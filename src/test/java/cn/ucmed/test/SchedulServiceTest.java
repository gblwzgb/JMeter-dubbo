package cn.ucmed.test;

import cn.ucmed.rubik.branch.view.TFBranch;
import cn.ucmed.rubik.department.view.TFDepartment;
import cn.ucmed.rubik.doctor.view.TFDoctor;
import cn.ucmed.rubik.genre.view.TFGenre;
import cn.ucmed.rubik.schedul.service.ISchedulService;
import cn.ucmed.rubik.schedul.view.TFSchedul;
import cn.ucmed.util.DubboInit;
import org.junit.Before;

import java.util.List;

/**
 * Description:
 * Author: lxl
 * Date: 2017/4/26 16:28
 */
public class SchedulServiceTest {

    private ISchedulService schedulService;

    @Before
    public void setupTest() {
        DubboInit init = DubboInit.getInstance();
        init.initApplicationContext();
        schedulService = (ISchedulService) init.getBean("schedulService");
    }

    //@Test
    public void runTest() {
        TFDoctor doctor = new TFDoctor();
        doctor.setDoctId("528");
        List<TFSchedul> scheduls = schedulService.getSchedul(new TFBranch(), new TFDepartment(), doctor, new TFGenre());
        System.out.println(scheduls);
    }
}
