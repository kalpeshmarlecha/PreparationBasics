package com.ebay.crm.kycriskrtr;

import com.ebay.testinfrastructure.dbautil.dbdriver.ConfigHosts;
import com.ebay.testinfrastructure.dbautil.dbdriver.DBHost;
import com.ebay.testinfrastructure.dbautil.dbdriver.SQLDBDriver;
import com.ebay.testinfrastructure.dbautil.dbdriver.Table;
import com.ebay.testinfrastructure.dbautil.dbdriver.TokenMismatch;
import com.ebay.testinfrastructure.params.TestParams;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.ITestContext;

import com.ebay.crm.common.data.UserDataObject;
import com.ebay.crm.common.data.misc.UserCreation;
import com.ebay.crm.common.data.util.AuthTokenUtil;
import com.ebay.crm.common.db.UserHostInfo;
import com.ebay.crm.common.report.CrmReport;
import com.ebay.crm.common.util.ThreadHelper;
import com.ebay.crm.kycriskrtr.datacreation.DataObject;
import com.ebay.crm.kycriskrtr.datacreation.OAuthClient;
import com.ebay.crm.kycriskrtr.datacreation.TestInputs;
import com.ebay.crm.kycriskrtr.datacreation.UserAgreementUpdateRequest;
import com.ebay.crm.kycriskrtr.datacreation.UserSiteProgramRampDBModule;
import com.ebay.crm.kycriskrtr.util.PaymentMockUtil;
import com.ebay.crm.kycriskrtr.util.TestNGUtil;
import com.ebay.quality.testdataautil.common.EasyFilter;
import com.ebay.quality.testdataautil.type.RegistrationType;
import com.ebay.quality.testdataautil.type.TestObject;
import com.ebay.quality.testdataautil.type.User;
import com.ebay.testinfrastructure.dbautil.dbdriver.ConfigHosts;
import com.ebay.testinfrastructure.dbautil.dbdriver.DBHost;
import com.ebay.testinfrastructure.dbautil.dbdriver.SQLDBDriver;
import com.ebay.testinfrastructure.dbautil.dbdriver.TokenMismatch;
import com.ebay.testinfrastructure.serviceautil.rest.MediaType;
import com.ebay.testinfrastructure.serviceautil.rest.Response;
import com.ebay.testinfrastructure.serviceautil.rest.RestfulClient;

public class BaseTestPlan {

    private static final String TEST_CASE_ID = "testCaseId";
    private static final String CONSUMER_SCOPE_APP = "https://api.ebay.com/oauth/scope/core@application";
    private static final String CONSUMER_SERVICE_SCOPE_APP = "https://api.ebay.com/oauth/scope/customerservice@application";


    public String getAbsoluteFilePath(String fileName) {
        File file = new File("");
        return Paths.get(file.getAbsolutePath(),
                "src", "main", "java", "com", "ebay", "crm", "kycriskrtr",
                fileName).toString();
    }

    public void createSeller(DataObject dataObject) throws Exception {
        System.out.println("creating Seller");
        UserCreation uc = new UserCreation();
        RegistrationType regType = uc.findRegType(dataObject.getTestInputs().getRegSite());
        User seller = (User) uc.createUser(regType, false);
        UserDataObject udo = new UserDataObject();
        udo.setUser(seller);
        udo.setUserId(Long.valueOf(seller.getId()));
        udo.setHostInfo(new UserHostInfo(seller.getId()));
        dataObject.getTestInputs().setUserDataObject(udo);
        dataObject.getTestInputs().setSeller(seller);
        dataObject.setSeller(seller);
        System.out.println("seller create " + seller + " ::" + seller.getId() + " site: " + regType.name() + " user host: " + dataObject.getUserHostInfo());
    }



    public void createMockUserAgreement(Long userId, String agreementName, String agreementStatus, DataObject dataObject, int countOfRetry) throws Exception {
        String HOST = TestNGUtil.getTestParam("userAgreementHostName");
        String REST_API = "/identity/user_agreement/v1/agreement/" + agreementName + "/cs_update_status";
        UserAgreementUpdateRequest userAgreementUpdateRequest = new UserAgreementUpdateRequest();

        userAgreementUpdateRequest.setAgreementStatus(agreementStatus);


        userAgreementUpdateRequest.setAgreementName(agreementName);
        if (dataObject.getTestInputs().getRegSite().getId() == 0) {
            userAgreementUpdateRequest.setActiveSiteId("EBAY_MAIN");
        } else if (dataObject.getTestInputs().getRegSite().getId() == 77) {
            userAgreementUpdateRequest.setActiveSiteId("EBAY_DE");
        } else {
            userAgreementUpdateRequest.setActiveSiteId("EBAY_UK");
        }

        userAgreementUpdateRequest.setUserId(userId + "");
        userAgreementUpdateRequest.setModifiedBy("CUSTOMER_SUPPORT");

        UserSiteProgramRampDBModule userSiteProgramRampDBModule = new UserSiteProgramRampDBModule();
        String siteId = dataObject.getTestInputs().getRegSite().getId()+"";
        userSiteProgramRampDBModule.insert(userId + "",
                dataObject.getTestInputs().getUserHostInfo().getUserWriteHost().getHostId()+"",
                "10", siteId, "1", null);


        RestfulClient client = new RestfulClient.Builder()
                .enableConsoleLogging(true).build();
        
        
        insertIntoUserAgreement(userId, siteId, 20, 1, 1, dataObject.getTestInputs().getUserHostInfo().getUserWriteHost().getHostId());

        /* 
       Response response = client
                .request()
                .payload(userAgreementUpdateRequest)
                .header("Authorization",
                        "Bearer "
                                + generateToken(
                                "urn:ebay-marketplace-consumerid:0e1c547f-7a40-422d-90fb-39e8e933adcc",
                                "fd15fd11-6d5f-4d29-83da-fbe0d79d1089",
                                CONSUMER_SERVICE_SCOPE_APP))
                .contentType(MediaType.JSON).accept(MediaType.JSON).build()
                .post(HOST + REST_API);

       /* if (!response.isSuccessful()) {
            System.out.println("Unable to update User Agreement. Raw HTML below. Already tried " + countOfRetry + " times.");
            System.out.println(response.toString());
            if (countOfRetry < 10) {
                return createMockUserAgreement(userId, agreementName, agreementStatus, dataObject, ++countOfRetry);
            } else {
                throw new IllegalStateException("Failed : HTTP error code : "
                        + response.statusCode() + " " + response.reasonPhase() + " " + response.asString());
            }
        }*/

    }

    public static void insertIntoUserAgreement(Long oracleId, String siteId, int agreementId, int agreementAccepted, int status, int hostId) throws Exception {
		SQLDBDriver driver=new SQLDBDriver();
		
		StringBuffer sbHostName = new StringBuffer();
		sbHostName.append("UserWrite" + hostId + "HostPhysical"); // Built the host name like this UserWrite10HostPhysical
		DBHost lookUpHost = ConfigHosts.getInstance().getDBHost(sbHostName.toString());
		String exptDataInsertQuery = "insert into  USER_AGREEMENT_LOG (USER_ID, SITE_ID, AGREEMENT_ID, CREATION_DATE, IS_AGREEMENT_ACCEPTED, AGREEMENT_DATE, AGREEMENT_STATUS, AGREEMENT_VIEW_COUNT, LAST_MODIFIED_DATE) values ("
				+ oracleId + ","+siteId+","+agreementId +",sysdate, " + agreementAccepted +", sysdate, " + status +", null, sysdate)";
		System.out.println(exptDataInsertQuery);
		driver.executeUpdate(lookUpHost, exptDataInsertQuery);
		ThreadHelper.waitForSeconds(2);
	}

    public Response createMockPaymentAccount(Long userId, String accountType, String userType, int countOfRetry) {
        String HOST = TestNGUtil.getTestParam("paymentAccountGatewayHostName");
        String REST_API = "/core/payments/v1/payment_account/create";

        StringBuilder restParameters = new StringBuilder();
        restParameters.append("{\"userId\":\"" + userId);
        restParameters.append("\",\"accountType\":\"" + accountType);
        restParameters.append("\",\"userType\":\"" + userType + "\"}");

        RestfulClient client = new RestfulClient.Builder()
                .enableConsoleLogging(true).build();
        Response response = client
                .request()
                .payload(restParameters.toString())
                .header("Authorization",
                        "Bearer "
                                + generateToken(
                                "urn:ebay-marketplace-consumerid:0e1c547f-7a40-422d-90fb-39e8e933adcc",
                                "fd15fd11-6d5f-4d29-83da-fbe0d79d1089",
                                CONSUMER_SCOPE_APP))
                .contentType(MediaType.JSON).accept(MediaType.JSON).build()
                .post(HOST + REST_API);

        if (!response.isSuccessful()) {
            System.out.println("Unable to create Payment Account. Raw HTML below. Already tried " + countOfRetry + " times.");
            System.out.println(response.toString());
            if (countOfRetry < 10) {
                return createMockPaymentAccount(userId, accountType, userType, ++countOfRetry);
            } else {
                throw new IllegalStateException("Failed : HTTP error code : "
                        + response.statusCode() + " " + response.reasonPhase());
            }

        }

        return response;
    }

    private Properties buildHeader(DataObject dataObject) throws Exception {
        Properties header = new Properties();
        header.put("Authorization", AuthTokenUtil.fetchAppToken());
        header.put("X-EBAY-C-MARKETPLACE-ID", "EBAY_MAIN");

        StringBuilder userId = new StringBuilder();
        userId.append("origUserId=origUserName%3D")
                .append(dataObject.getTestInputs().getSeller().getUserLoginName())
                .append("%2CorigAcctId%3D")
                .append(dataObject.getTestInputs().getSeller().getId());
        header.put("X-EBAY-C-ENDUSERCTX",userId.toString());

        CrmReport.logStep("Request header X-EBAY-C-ENDUSERCTX=" + userId.toString());
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");
        return header;
    }

    public String generateToken(String consumerId, String secret, String scope) {
        String token = "";
        try {
            OAuthClient client = new OAuthClient(new URL(
                    "https://oauth.qa.ebay.com/identity/v1/oauth2/token"));
            Properties headers = new Properties();
            headers.put("Content-Type", "application/x-www-form-urlencoded");
            headers.put(
                    "Authorization",
                    "Basic " + Base64.encodeBase64URLSafeString((consumerId + ":" + secret).getBytes())
            );
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("grant_type",
                    "client_credentials"));
            params.add(new BasicNameValuePair("scope", scope));
            token = client.getAppToken(headers, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public String getDbHostName(DBHost triggerEventDbHost, Long sellerId) {
        String dbHostName = "userrisk1" + sellerId % 10 + "host";
        System.out.println("get dbHostName: " + dbHostName);
        return dbHostName;
        //return triggerEventDbHost.getName().split("StagingDB\\.")[1].split("Physical")[0].toLowerCase();
    }

    public String getTriggerEventPayload(DataObject dataObject) throws ParseException {
        String sellerId = dataObject.getSeller().getId();
        TestInputs testInputs = dataObject.getTestInputs();
        String eventType = testInputs.getEventType();
        String payload = null;
        System.out.println("\n" + eventType + "\n");
        if (eventType.equalsIgnoreCase("KYC.STATE.UPDATE")) {
            payload = "userId=" + sellerId
                    + "|checkType=" + testInputs.getKycCheckType()
                    + "|fromState=" + testInputs.getKycFromState()
                    + "|toState=" + testInputs.getKycToState();
        } else if (eventType.equalsIgnoreCase("KYC.RISK.FLOW.EVAL")) {
            payload = "userId=" + sellerId
                    + "|payAccActionType=" + testInputs.getPayAccActionType()
                    + "|payAccActionExecutionDate=" + PaymentMockUtil.convertToISOTime(testInputs.getPayAccActionExecutionDate())
                    + "|nextFlowEvalCheckPoint=" + testInputs.getNextFlowEvalCheckPoint()
                    + "|nextFlowEvalDate=" + PaymentMockUtil.convertToISOTime(testInputs.getNextFlowEvalDate());
        } else if (eventType.equalsIgnoreCase("KYC.RISK.DCN.OUTCOME")) {

        }
        System.out.println("\n" + payload + "\n");
        return payload;
    }

    public EasyFilter getTestCaseIdFilter(ITestContext testContext,
                                                 String testCaseId) {
        String testCaseid = testContext.getSuite().getXmlSuite()
                .getParameters().get(TEST_CASE_ID);
        testCaseId = getRange(testCaseId);

        String testsFromSystemEnv = System.getenv(TEST_CASE_ID);
        if(null != testsFromSystemEnv)
            testCaseId = getRange(testsFromSystemEnv);

        String testsFromSystemProps = System.getProperty(TEST_CASE_ID);
        if(null != testsFromSystemProps)
            testCaseId = getRange(testsFromSystemProps);

        Set<String> testcaselist = new TreeSet<String>();

        if (testsFromSystemProps != null && testsFromSystemProps != "") {
            String[] testcasels = testsFromSystemProps.split(",");
            for (String id : testcasels) {
                testcaselist.add(id.trim());
            }
        }

        if (testCaseId != null && !testCaseId.isEmpty()) {
            String[] testcasel = testCaseId.split(",");
            for (String id : testcasel) {
                testcaselist.add(id.trim());
            }
        }

        String[] ids = testcaselist.toArray(new String[0]);
        if (ids.length == 0)
            return null;

        EasyFilter filter = EasyFilter.in(TestObject.TEST_CASE_ID, ids);
        return filter;
    }

    private String getRange(String testcaseid) {
        if (testcaseid != null && !testcaseid.isEmpty()) {
            Set<String> testList = new TreeSet<String>();
            String[] tests = testcaseid.split(",");
            for (String test : tests) {
                if (test.contains("-")) {
                    String[] rangeList = test.split("-");
                    int min = Integer.parseInt(rangeList[0]);
                    int max = Integer.parseInt(rangeList[1]);
                    for (int i = min; i <= max; i++)
                        testList.add(String.valueOf(i).trim());
                } else
                    testList.add(test.trim());
            }
            return testList.toString().replace("[", "").replace("]", "");
        }
        return null;
    }
}
