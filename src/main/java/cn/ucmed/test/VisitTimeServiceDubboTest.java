package cn.ucmed.test;

import cn.ucmed.rubik.department.view.TFDepartment;
import cn.ucmed.rubik.doctor.view.TFDoctor;
import cn.ucmed.rubik.genre.view.TFGenre;
import cn.ucmed.rubik.schedul.view.TFSchedul;
import cn.ucmed.rubik.visittime.service.IVisitTimeService;
import cn.ucmed.rubik.visittime.view.TFVisitTime;
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
public class VisitTimeServiceDubboTest extends AbstractJavaSamplerClient {

    private IVisitTimeService visitTimeService;

    private long start = 0;//记录测试开始时间；
    private long end = 0;//记录测试结束时间；
    private String doctorId;
    private String clinicDate;

    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sr = new SampleResult();
        setValues(javaSamplerContext);
        sr.sampleStart();
        start = System.currentTimeMillis();
        try {
            TFDoctor doctor = new TFDoctor();
            doctor.setDoctId("528");
            TFSchedul schedul = new TFSchedul();
            schedul.setClinicDate("2017-05-10");
            List<TFVisitTime> visitTimes = visitTimeService.getVisitTimeList(new TFGenre(), doctor, new TFDepartment(), schedul);
            sr.setSuccessful(true);
            sr.setResponseData(visitTimes.toString(), null);
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
        visitTimeService = (IVisitTimeService) init.getBean("visitTimeService");
    }

    public void setValues(JavaSamplerContext arg0) {
        doctorId = arg0.getParameter("doctorId", null);
        clinicDate = arg0.getParameter("clinicDate", null);
    }

    @Override
    public void teardownTest(JavaSamplerContext arg0) {
        end = System.currentTimeMillis();
        getLogger().info("    cost time: " + (end - start) + "ms");
    }
}
