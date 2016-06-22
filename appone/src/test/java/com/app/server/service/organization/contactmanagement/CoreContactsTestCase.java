package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.contactmanagement.CoreContacts;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.contactmanagement.CommunicationData;
import com.app.shared.organization.contactmanagement.CommunicationType;
import com.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;
import com.app.shared.organization.contactmanagement.CommunicationGroup;
import com.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Title title = new Title();
        title.setTitles("YINnBy9wTel79rqkNrQxAYsj6vipnstIfDRMnkVGbpu72It3eN");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setUtcdifference(10);
        timezone.setTimeZoneLabel("7PlYfOtn2f2kYWJcxj6GpTUyKRPHuVecfcdR1te1VH2HsoPbkT");
        timezone.setCountry("ca4L5Kk8wJ9cYv14vIYJyP35aAOKOlwDpoczLsWdq0qqrfzczI");
        timezone.setGmtLabel("jrdpVR6OsNdizNXm9J7WxoqkUp2dmAqcBSrY2BJUT1TpqwRZBj");
        timezone.setCities("vdh6u0pCa9LcAYmtLhaOaarg7PuAj53D2Jh3xYh1ePIbdyjmql");
        Gender gender = new Gender();
        gender.setGender("mPZQOwRqnxxG6as2uqniwKvVEPdBej8tFR7hVdc87rxFrVZOWp");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setLanguage("cbPGoEEk6yilBDsX9u9YztN0a1ErkttGc9A4lOOfNQNk6M6Aoi");
        language.setLanguageIcon("j8WpMDiOM7GmwBTP0RlBLrBo279Kco1eZJxb13rItah6vtXwd0");
        language.setLanguageDescription("hvq7OSmX7PfXuZRfzJvFzNbbH1TbZFgVLSAE68dejHEUni8Adx");
        language.setAlpha3("YWS");
        language.setAlpha2("Io");
        language.setAlpha4parentid(8);
        language.setLanguageType("pxIiXsQ7hgF5EFIu9gV5otk5aU0SJGHi");
        language.setAlpha4("C3y6");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setPhoneNumber("wovc6zCahIInYB7jyGKG");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1466582768964l));
        corecontacts.setNativeMiddleName("ZcY8z0OCHsoODcpHDJ5Njl5TelaMaV5SzL6p26RjN17A7oZPO0");
        corecontacts.setAge(35);
        corecontacts.setMiddleName("duMk1jzrlfAWMTa4H9UMTVSCoNcJkmWvqqi5h4XGpqx3xYcUxP");
        corecontacts.setFirstName("MlTyJXLcKGWVCDad48cJL91tsrkAcJMX3DsPJaPNRRjlsxnGnD");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeFirstName("yV1l1XUn2QoWxh7Bm9y9zIIUQnAWB3bb6Kgq7Ve6Lj1ombrxR6");
        corecontacts.setLastName("n829yxy0laqbcftzRpPAlH2AFE3bgVWvf6jD1sYAm0NgNSc4tv");
        corecontacts.setNativeTitle("NtKw5pNc4qtaGZCpplpXTw6mAMUgZWA4DEnlm1bFG9v6gGcYYq");
        corecontacts.setEmailId("0Z6kJLWDXJWrwLzb9Xp5lFB5UDqIFQ9wqYUBbNuskmVNaakddd");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1466582769142l));
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLastName("zC6e7O6c9xq9APq5c0mF3NX6Gbsm8vTtocF85VOoM5GlnPvFNy");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeDescription("kUJCU56M4yPRJWaovpexuxoHgFEO60iVzu2EQN11ywXflDIIxz");
        communicationtype.setCommTypeName("8Gy7q3Owh7PBGj6dqU6NE6YrSwUuQOpJ3HV5jrmmubb4GjqsLj");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("LvXmroAQlkorGDffFmRs9QNXe80DpQ4X8xem1xXPiXMlp3tTPM");
        communicationgroup.setCommGroupDescription("4r5hgnwsTo7a7SLjkrjE1SeqyfeBR6EhlrhmzjAOqlrlEjdi82");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeDescription("2WNUqZ4Y3I1rCD5wIPrfFLnSXTxGO6tKYZvGXDrmI8vBhb86vH");
        communicationtype.setCommTypeName("goFiGXVTi6xqXp38Rm13XFnnhwS6bIEvHMWpYWzZGuIAHhOKyB");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("hCv3hBuxgT");
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress2("sOk3KjufjIjTYy7cUu1LYGTXgGPkG1VPjbNiMNDT6ubmeKVUdI");
        address.setLongitude("qALONvvX2xc9uxRwi9vYBCrn2II5MZHi5iVhBcbqACR6znSRO6");
        address.setZipcode("gm0M9b");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("HdBVEbcAyYjUEXL2iH8ENb8N2EnbKkBC7VGolheEG9CUPAjXgc");
        addresstype.setAddressTypeIcon("cspmCVfXSw1sFErMZtEX7Yutj3qIDsRfV5O4g1kM0pR1hKPPGU");
        addresstype.setAddressType("HZPhN5g09hhuRfA0lHX3RzX1ff7aJnGsLoqys3YSRJ7l8OETTy");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Country country = new Country();
        country.setCountryCode1("MPr");
        country.setCapitalLatitude(2);
        country.setCurrencyCode("HiU");
        country.setIsoNumeric(694);
        country.setCapital("xXN1JzI8oigANd2oMdMVMxoLx2fQYA2V");
        country.setCountryCode2("Jhe");
        country.setCapitalLongitude(5);
        country.setCountryName("Z3OakV1Tj7BmRx5wooDojUoIWMWGVMlYuqNe7r53DA26TFhrga");
        country.setCurrencyName("nNtyd3pQKwM15X580rofxfM8YvyVTlURCccO1nCwvQFDFsCKm2");
        country.setCountryFlag("HIVC1v0kL1jr2psJ5IDLFvxD9LeolW8shXkB3iRRVwCIsTowtu");
        country.setCurrencySymbol("D78jED0w2BDxDFx5cf55eOZNCKb3um85");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("RP1Y8rD3NDAz2VwMdTl9HMsf9gNLsA7JdUJiFq9idrYRJj4DAz");
        state.setStateFlag("TqvYOjpEqGA3R8UPrrFsY8mPckLiiqhGXYGrCoNMde0RmfgOU5");
        state.setStateCapital("70NLwP4Ad0ViSqzU2fnANnUghETzJFwwhmT2YrkrolxX9fsclI");
        state.setStateCapitalLongitude(2);
        state.setStateCodeChar3("1NiNVG5Be134x8cPF5k9WJve1G4y7JV6");
        state.setStateCode(1);
        state.setStateCodeChar2("G53p90HvWeKAXSEDrHecmMNWVJTBeVZX");
        state.setStateCapitalLatitude(5);
        state.setStateDescription("pwgtmqGGQ9hEh8BZssCLCWu4qfubTgndshbFGu7a730eOb5sZ3");
        state.setStateName("QVs7RIrGCJs8wBfRDqXTxIYGTAfbguyQdXxe1811LuNMF3k961");
        state.setStateFlag("a9SlNH6b2aue4M1XagPhlblB9U1Y9GI0IdDxMEbzJpswBKmzKd");
        state.setStateCapital("qWtXpxov3tKdvFaLZjqasgaGP2GDEiqQUrO2YZ2GXUhW99sXLq");
        state.setStateCapitalLongitude(2);
        state.setStateCodeChar3("Kpn0PYhpRQzY74CiYJYiOnUZUsgYIW0Q");
        state.setStateCode(1);
        state.setStateCodeChar2("TwfvRoWqa8KpynfrgLIzuxnZveDbAOiR");
        state.setStateCapitalLatitude(11);
        state.setStateDescription("oTCn94FCtMliyQ3mxfQPw5glYhqb7ayWjrgku4q3dyMb0J3Pf4");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(11);
        city.setCityLatitude(10);
        city.setCityLongitude(6);
        city.setCityLatitude(9);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("HLZk5PQg2opmt6tP6TqA9nauqZ3RPMlrfVi3rHthMEzufmvivs");
        city.setCityCodeChar2("7E30CeCfmhjc3oiOKaxR3hsYwpouTAdr");
        city.setCityCode(1);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("9pjzk3dlz62TKPXkslWMkzuCtFAu8ZnghynIf32HkBTmyQ5GG7");
        city.setCityName("vxevanXniRsz72fHMNO7EqFjubIVeIV3OvMrJ7m2BQuQX94TIo");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddress2("x7ulDvXq9a1ZovKlxQ663on0n9oViRBmQNSEexuyqnbvdRREku");
        address.setLongitude("aGj0W4P5dQrUQ7EYO8TY1njvJq8deA9qg2pZQPfaLp6YvA0tba");
        address.setZipcode("3dGJuA");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("eErxshJD7xSHtWr04tOOa9pflsYyL12ON8x1J0WAE7WHEnBF71");
        address.setAddress3("7sKuQraBK5SnQZB60x6lI3tzX10KHhLJHi6UydksTq6H9YrcTP");
        address.setLatitude("ZSegzt98tsEfJO5YzTjU7rZB218NKIweSTITU3gWGdzmTn0F5O");
        address.setAddressLabel("uxvqzCxxYEN");
        address.setCityId((java.lang.String) CityTest._getPrimarykey());
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setPhoneNumber("gJV5ee57hM28JpFCvhIt");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1466582770216l));
            corecontacts.setNativeMiddleName("RHTdcAWxEz9V0xyW051deQue6UjgRR97XzaRNPgrfEB2nunFlw");
            corecontacts.setAge(68);
            corecontacts.setMiddleName("zswVppcv1Qhie1UvVZpWRJzhiliYMcu1mQisfBMnN5X1OcroDh");
            corecontacts.setFirstName("Ff0DQqmecD4dTIYR3OWMWrEmZk74Au4wn8k3fs4VyLHTbtnRwE");
            corecontacts.setNativeFirstName("9pPrBCyMBbz64XLmToy47kVGmeAB2X4RkMtAOvn4BzMdYDCP9b");
            corecontacts.setLastName("tHqvcaORkoiXxpHd2SfVpkiqXjpsrMd16sHnp1GcHVZdoPL3gd");
            corecontacts.setNativeTitle("dHZPALah6d9W7empem58tp5pTwpfN6Wf75IebtrhXff7yiRDpM");
            corecontacts.setEmailId("GRQ5UzXZFHXKkURzT5n28DU4gfqzprpa1KHusXmbe4hqVxjJFO");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1466582770518l));
            corecontacts.setVersionId(1);
            corecontacts.setNativeLastName("JSTViT1ppKcYllULycFs3YF6IArwI79SiHs9EAe92w2HA3uIfH");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "RiclJAWqGM5B2EsDhmYID1GKO0KF4yWmcjnHTJrF1E6nBWJ7s81zPLzIISFc5L5XIfNeSnCR0xuYadqXjCMfrRTU5aXxaLGMEn9HT8Wo8O6rQpwbgPImIr7iuvoRCcMy53zbxXrzcNuFjkfkkIQ7A3D2XcSkwKljZDPnBQrgclthKXDsZ29f6IXtXVKQPAnl5wLNEFjXb3DVnA0UQHydXGaRrZ9o6QtebnRTZctsaXqHj1X21MWFcxnMRoxiQ7PJl"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "6teFVapJ6ibI6ZOhALfPG4pp6iewUHLPtOcXwjz6UlXkJAUmqHmhuLZXlhKM6VnmVpjOYRbF2dzCN8yY3Y2SnlYJ9VNMpjNCWuh6tXDqAXttz8NptJ5ZAcjLZtwv0Bz68zmsPQxmCsI6FZqTh9Xrxw17ddMjd6XmuIuk8LLi3O8kYhVcAzWGXsbakj7DqKQQ2V3BE7JCztxswetgVyMoSjdl3qiKLLDsz64BCXI3LFQRawFv1X3G7xvYYDeA7jjjZ"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "QKxsvCkhIiPoWI1Vdxa9KF2cUZYolazP2uE0UZtW6eiTZuNEH0WSZWDhJGk5a6FyYlzsoc7LcmlWl5qitIVSze6QnAl0cfNgsNxEFHYc950JA5mxKTnwWN31VR4dAJd4p6RSGV82BqWghXClT3HXmQfwVUmdoxSZ2O4xiAKSi134OmJ3UdzHU5qq8W89ciOUafXtNSeDNfARpPJlvIgHAucrTxcb5x8mlgdqxwhCf3Tzf2FQFiwBqblBaCKkHWOmL"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "6DEXonTBi9kRmNN9MMoBZTzqM5pHLnrJ32Hj2WDvIh85UZnOaNgn9kUP7Zk2YrxmA"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "E2xkMOh7oLtgJ9apyKecmaJOGSkw4Hih4nOMb4QuLOOoLgJWA9oI9TagZL3uaQjjUYXxQxPtjVVWF8A8QfXkvrcpeyLTP0OvFxuWdx26NcUOlcC7l6FZcFFKIbbBBou2R8RINf4JIjLLljJqgn3B3gMSp2L0I4O5lEF607Bz1yvc1hxWymF28ggmncEmoSII4h4j2YTjZiiXlnzpO7J1ObzBV7j4LPQj3KuIMrA4fXupv7cyvOrCV6Nn31NQ8qJVk"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "mAiEnySJjKfF1UAB7ule998TgvsyDwzJUhvxR7k4K884O6UHEPIp4U1Uik7LJvOfSZZALYvG6SikP1YpN6DrF79xTMh47EJZZ0HbM8BgNUzAIrueNEdXbQ3GhX8fI1qIvPHdn0FstOPaJgv6exOJ4hkV9ch53CMffwlbCaIV5QLfeDzWSVKJXrw5RKQuRDI8qcYBkMpwzFzlIlIJO3ynzBvc3jlB298taxQD9gCwxxrXoc0yfKFh5lkKElDYBhgvW"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "jvN6hJhQswmgHugPsl5pgTyLoq7OlTMJji3DLg1QdSOgJT63D7TowU6u1kcXPLjjYNYi8sPrAJvBiUXfQalQYhpGdJN6PaIaCmzSK7qz16p3C3QAl5djdDvrqUK9dUcFj2oZsXUIUxeSw10emI8IIlbswnxZn7p5wl8gURLkFouxzvYkKDceMQR7iQYeUTQSv9Aho0gDUOVaXPlhEyQfb8ujW2xMlIibdLA7VW4mvvCSX4MpUgSmBiL3Cdtxysi8Y"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 250));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "PxpIW0h0Tk7IhIcalToUZPxt9geFHhQ0n1bWlWK7khWSAx5AfXm3jllg7fN9PCbuLWy0653ElwmvS2nbTARylUj55sJnooKwuRpVwZmx6BP6WJIK3PeS4fHKzQNY9AADA6N6xfcTl1Q8HC7lbkVm0hicOFkuI1DaVGtMA75wbs3ZH294v0JO0hg6eU2tHKb7k7htHYbYeR0niPcOmtHQFi1q3KowlKfG1dAeL5Q4mGf7wBROdn8Rq5YQZ3W0br4Pi"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "acP3Zb3CjsWO38mL8RQTU"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
