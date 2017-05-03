package cn.ucmed.test;

import cn.ucmed.rubik.department.view.TFDepartment;
import cn.ucmed.rubik.doctor.view.TFDoctor;
import cn.ucmed.rubik.genre.view.TFGenre;
import cn.ucmed.rubik.schedul.view.TFSchedul;
import cn.ucmed.rubik.visittime.service.IVisitTimeService;
import cn.ucmed.rubik.visittime.view.TFVisitTime;
import cn.ucmed.util.DubboInit;
import org.junit.Before;

import java.util.List;

/**
 * Description:
 * Author: lxl
 * Date: 2017/4/26 16:28
 */
public class VisitTimeServiceTest {

    private IVisitTimeService visitTimeService;

    @Before
    public void setupTest() {
        DubboInit init = DubboInit.getInstance();
        init.initApplicationContext();
        visitTimeService = (IVisitTimeService) init.getBean("visitTimeService");
    }

    //@Test
    public void runTest() {
        TFDoctor doctor = new TFDoctor();
        doctor.setDoctId("528");
        TFSchedul schedul = new TFSchedul();
        schedul.setClinicDate("2017-05-10");
        List<TFVisitTime> visitTimes = visitTimeService.getVisitTimeList(new TFGenre(), doctor, new TFDepartment(), schedul);
        System.out.println(visitTimes);
    }
}
