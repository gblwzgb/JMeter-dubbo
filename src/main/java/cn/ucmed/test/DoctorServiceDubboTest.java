package cn.ucmed.test;

import cn.ucmed.rubik.branch.view.TFBranch;
import cn.ucmed.rubik.department.view.TFDepartment;
import cn.ucmed.rubik.doctor.service.IDoctorService;
import cn.ucmed.rubik.doctor.view.TFDoctor;
import cn.ucmed.rubik.genre.view.TFGenre;
import cn.ucmed.rubik.schedul.view.TFSchedul;
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
public class DoctorServiceDubboTest extends AbstractJavaSamplerClient {

    private IDoctorService doctorService;

    private long start = 0;//记录测试开始时间；
    private long end = 0;//记录测试结束时间；
    private String deptId;

    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sr = new SampleResult();
        setValues(javaSamplerContext);
        sr.sampleStart();
        start = System.currentTimeMillis();
        try {
            TFDepartment department = new TFDepartment();
            department.setDeptId("05");
            List<TFDoctor> doctors = doctorService.getGeneralDoctorList(new TFBranch(), department, new TFSchedul(), new TFGenre());
            sr.setSuccessful(true);
            sr.setResponseData(doctors.toString(), null);
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
        doctorService = (IDoctorService) init.getBean("doctorService");
    }

    public void setValues(JavaSamplerContext arg0) {
        deptId = arg0.getParameter("deptId", null);
    }

    @Override
    public void teardownTest(JavaSamplerContext arg0) {
        end = System.currentTimeMillis();
        getLogger().info("    cost time: " + (end - start) + "ms");
    }
}
