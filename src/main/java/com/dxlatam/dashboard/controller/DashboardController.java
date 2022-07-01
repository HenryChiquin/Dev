package com.dxlatam.dashboard.controller;

import com.dxlatam.dashboard.domain.dto.Response;
import com.dxlatam.dashboard.domain.entity.GetDashboard;
import com.dxlatam.dashboard.domain.service.GetDashboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class DashboardController {
    private final static Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    GetDashboardService getDashboardService;

    @GetMapping("/dashboard")
    public ResponseEntity<List<GetDashboard>> getApiDashboard(){
        List<GetDashboard> list = getDashboardService.list();

        if(list==null || list.isEmpty()){
            getDashboardService.save(new GetDashboard("activo",HttpStatus.OK.value(),1,0,1));
            list = getDashboardService.list();
        }else {
            int idDashboardOk = 0;

            try{
                for (var element:list     ) {
                    if(getDashboardService.findByCodigo(element.getCodigo()).get().getCodigo() == 200 )
                        idDashboardOk = getDashboardService.findByCodigo(element.getCodigo()).get().getId();
                }

                logger.info("Codigo de solicitud: "+idDashboardOk);
                GetDashboard getDashboard = getDashboardService.getOne(idDashboardOk).get();
                getDashboard.setInvocado(getDashboard.getInvocado()+1);
                getDashboardService.save(getDashboard);

            }catch (Exception ex){
                logger.info("Hubo un error " + list.size());
                int idErrorServer = 0;
                int idErrorServerOk = 0;

                for(int i=0; i< list.size(); i++){
                    GetDashboard getDashboard = list.get(i);

                    if(getDashboard.getCodigo() ==  200){
                        idErrorServerOk =getDashboardService.findByCodigo(getDashboard.getCodigo()).get().getId();
                        logger.info("Hubo un error 200: "+ idErrorServerOk);
                        GetDashboard getDashboard_exception = getDashboardService.getOne(idErrorServerOk).get();
                        getDashboard_exception.setExcepcion(getDashboard_exception.getExcepcion()+1);
                        getDashboard_exception.setInvocado(getDashboard_exception.getInvocado()+1);
                        getDashboard_exception.setExito(getDashboard_exception.getInvocado() - getDashboard_exception.getExcepcion());
                        getDashboardService.save(getDashboard_exception);
                    }

                    if(getDashboard.getCodigo() ==  500)
                        idErrorServer = getDashboard.getId();
                }

                if(idErrorServer!=0){
                    logger.info("Hubo un error 500 actualizar ");
                    GetDashboard getDashboardErrorServer = getDashboardService.getOne(idErrorServer).get();
                    getDashboardErrorServer.setExcepcion(getDashboardErrorServer.getExcepcion()+1);
                    getDashboardErrorServer.setInvocado(getDashboardErrorServer.getInvocado()+1);
                    getDashboardService.save(getDashboardErrorServer);
                }else{
                    logger.info("crear error 500 ");
                    getDashboardService.save(new GetDashboard("error server",HttpStatus.INTERNAL_SERVER_ERROR.value(),1,1,0));
                }
            }
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }

}
