package cn.ucmed.test;

import cn.ucmed.rubik.branch.view.TFBranch;
import cn.ucmed.rubik.department.service.IDepartmentService;
import cn.ucmed.rubik.department.view.TFDepartment;
import cn.ucmed.rubik.genre.view.TFGenre;
import cn.ucmed.util.DubboInit;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.util.List;

/**
 * Description:
 * Author: lxl
 * Date: 2017/4/27 9:46
 */
public class DepartmentServiceDubboTest extends AbstractJavaSamplerClient {

    private IDepartmentService departmentService;

    private long start = 0;//记录测试开始时间；
    private long end = 0;//记录测试结束时间；

    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sr = new SampleResult();
        sr.sampleStart();
        start = System.currentTimeMillis();
        try {
            List<TFDepartment> departments = departmentService.getMultiDepartmentList(new TFBranch(), new TFGenre());
            sr.setSuccessful(true);
            sr.setResponseData(departments.toString(), null);
            sr.setDataType(SampleResult.TEXT);
        } catch (Exception e) {
            getLogger().error("query departments response error : " + e.getMessage());
            sr.setSuccessful(false);
        } finally {
            sr.sampleEnd();
        }
        return sr;
    }

    //初始化操作
    @Override
    public void setupTest(JavaSamplerContext arg0) {
        DubboInit init = DubboInit.getInstance();
        init.initApplicationContext();
        departmentService = (IDepartmentService) init.getBean("departmentService");
    }

    @Override
    public void teardownTest(JavaSamplerContext arg0) {
        end = System.currentTimeMillis();
        getLogger().info("    cost time: " + (end - start) + "ms");
    }
}
