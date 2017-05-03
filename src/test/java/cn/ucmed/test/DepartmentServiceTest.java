package cn.ucmed.test;

import cn.ucmed.rubik.branch.view.TFBranch;
import cn.ucmed.rubik.department.service.IDepartmentService;
import cn.ucmed.rubik.department.view.TFDepartment;
import cn.ucmed.rubik.genre.view.TFGenre;
import cn.ucmed.util.DubboInit;
import org.junit.Before;

import java.util.List;

/**
 * Description:
 * Author: lxl
 * Date: 2017/4/26 16:28
 */
public class DepartmentServiceTest {

    private IDepartmentService departmentService;

    @Before
    public void setupTest() {
        DubboInit init = DubboInit.getInstance();
        init.initApplicationContext();
        departmentService = (IDepartmentService) init.getBean("departmentService");
    }

    //@Test
    public void runTest() {
        List<TFDepartment> departments = departmentService.getMultiDepartmentList(new TFBranch(), new TFGenre());
        System.out.println(departments);
    }
}
