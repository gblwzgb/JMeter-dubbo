package cn.ucmed.test;

import cn.ucmed.rubik.branch.view.TFBranch;
import cn.ucmed.rubik.department.view.TFDepartment;
import cn.ucmed.rubik.doctor.service.IDoctorService;
import cn.ucmed.rubik.doctor.view.TFDoctor;
import cn.ucmed.rubik.genre.view.TFGenre;
import cn.ucmed.rubik.schedul.view.TFSchedul;
import cn.ucmed.util.DubboInit;
import org.junit.Before;

import java.util.List;

/**
 * Description:
 * Author: lxl
 * Date: 2017/4/26 16:28
 */
public class DoctorServiceTest {

    private IDoctorService doctorService;

    @Before
    public void setupTest() {
        DubboInit init = DubboInit.getInstance();
        init.initApplicationContext();
        doctorService = (IDoctorService) init.getBean("doctorService");
    }

    //@Test
    public void runTest() {
        TFDepartment department = new TFDepartment();
        department.setDeptId("05");
        List<TFDoctor> doctors = doctorService.getGeneralDoctorList(new TFBranch(), department, new TFSchedul(), new TFGenre());
        System.out.println(doctors);
    }
}
