package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.app.shared.appbasicsetup.usermanagement.Login;
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
import com.app.shared.appbasicsetup.usermanagement.User;
import com.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        User user = new User();
        user.setIsDeleted(1);
        user.setPasswordAlgo("PTMrUxLTMDg9XOGPumQctViNJf5XSmEXk28TTlVgramO6acCnb");
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelIcon("WRtkyZC8Pzgb88lNJRLW6N3OtQgoU0Gh3UpR9UIQ5Gw4NKV9Mq");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelHelp("81WnMa7Hrpru8y9hG05TYHCQVsQoJwAbWn8UBzzOMWEunexMES");
        useraccesslevel.setLevelName("OlXwVy4sbBk4WiPoM1B9ZafiHGU6aeVT3YHZdK5UcawsKEGltC");
        useraccesslevel.setLevelDescription("ncirjsDLQc6S0kKeN47YEzlcAPrY8CVyxZaj9KhgNX7GXHpw1i");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainDescription("MIh5mgqfKK4RX7NFfit474fImumePuINhtTa1qLhiE0wMJdJoa");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("9Kb4HR8oiGMzZVftbiiFkeebgbGBdVqgK2xnMr24Y3DzomCXXv");
        useraccessdomain.setDomainIcon("PK3Zb6iRVnHaIQNrL4zDOhVps8udrm7KQ0m48pQ7d487lIPB6f");
        useraccessdomain.setDomainName("8Cx3gibZd7ELyMfAOnW3Pl2UJI4U7qzH90PKS982q1W7XVC2qe");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        user.setIsDeleted(1);
        user.setPasswordAlgo("wQDwXwdxbOzDpUUeHzcrBhBmsqHfeRO2ErxZiNZiKNnwP9Ahde");
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setAllowMultipleLogin(1);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsLocked(1);
        user.setGenTempOneTimePassword(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1466582801732l));
        user.setSessionTimeout(2312);
        user.setMultiFactorAuthEnabled(1);
        user.setChangePasswordNextLogin(1);
        user.setUserAccessCode(32190);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1466582801732l));
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("QhV6q2WUKvCR47FuG8uAREF5laMlOd68n1RRzFJEml0mSdNpRG");
        Question question = new Question();
        question.setQuestionIcon("ONZbl0Fdq47HBvE83qZIUfYUJWxZ2saatgWaTVworhmq7yF2sz");
        question.setLevelid(1);
        question.setQuestionDetails("1QTqmEBWiW");
        question.setQuestion("4pMpBjer93yy1DNQNq6mJM0YmC0z8bgvpOuaUJVJw0rWS2GYw8");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("FL9bWSe1tUfEvB0mzrZDTUVPTbzkV7IcabAInRy6246yCx0azs");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setUser(user);
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePasswordExpiry(3);
        userdata.setOneTimePassword("LqgwlNcZ0JGuytjbBIfrIPOPuWep613C");
        userdata.setPassword("ky2ewBWINeb2yAKL9gARqjsshIHaUpZ1WTBjImNo3qboiCReGK");
        userdata.setLast5Passwords("3SX861Gl9qIjPXRLiC9cWUeHkFDY26WpR3YJCYATky7OZWcMAu");
        userdata.setOneTimePasswordExpiry(5);
        userdata.setOneTimePassword("ICyJiUCYufxd6WA93hrMto2H1wkSKw31");
        userdata.setPassword("2YbNN8h34kztT3d9s8xlXNhoUQ8QQCp0v0K99rRLNMthLQqPtN");
        userdata.setLast5Passwords("UdYijxsVqIzF6ek3hPmFINPz4Bsf1G27hS4LI7v0grOKk9ziDt");
        userdata.setUser(user);
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1466582802337l));
        user.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setPhoneNumber("psEfhxN6VznuhikYpK56");
        Title title = new Title();
        title.setTitles("gehXfB9AF6LDRDV9jLCVL5QehhPdMrfkVnOOEM1ikgQPoITSNg");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setUtcdifference(6);
        timezone.setTimeZoneLabel("Vu02v1QswhjU5fZd4I4uoUce7z5ALyMjF9yKUS5otyttBNHTAv");
        timezone.setCountry("uiyYBSZ44OshbGD8g7PGr0Q5WwrDXihcguTxWqD5w3RlX1hKRE");
        timezone.setGmtLabel("etbaucjaUkM7SVMJuEBfLMQQZDV4uV4tw20W1Tz8CSals2GA9i");
        timezone.setCities("3aPX2xRSYY9Dsl2G3MRrwBIcbgRbMFrBGvvzqhFIabGpUdYbzr");
        Gender gender = new Gender();
        gender.setGender("FMeZ1VyFhRrxMtcRXWwdgDq4rnQQGa1duG5PP16kPEItvVccqh");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setLanguage("kzFtQ1RQd3zpfg8kW1LC8XALZPIT3yMFMNU9uoyu62WqTyeLeh");
        language.setLanguageIcon("7LgTItvAudSt5ETmrQwLLEAVB7IO0CzHcjEme9F38KpvLojoGY");
        language.setLanguageDescription("NGWcQvWWPoC3VtbflodfMfxlJWmcZlq5Tg4l4K1CbcvvkIQPQ5");
        language.setAlpha3("hqA");
        language.setAlpha2("T5");
        language.setAlpha4parentid(10);
        language.setLanguageType("LfSOW5FaCx561UdewJZrcg7R1shNkNhh");
        language.setAlpha4("SaRE");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        corecontacts.setPhoneNumber("YWfVOUHLCj4qyjlKfzJk");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1466582802675l));
        corecontacts.setNativeMiddleName("HyimYyCDn2ovrsSHKUFe9LlT7vjI1YWfnyaVK4sTByzakVZRas");
        corecontacts.setAge(107);
        corecontacts.setMiddleName("cjXbkZ063zU1jcwSbbmInH0RgItfY23S981eDwYpBOmdv4ftI6");
        corecontacts.setFirstName("ZEBfJjQwbi83TA2aCvpTMEp6NyCvSiSHjeRBkeE59j5kXcMAb2");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeFirstName("KzyzQCpxqD7WiIo8neKqSVvOxc3FYOCICXKCpaPhSCIQ74VAsF");
        corecontacts.setLastName("bGlhd2gtN3PltP6Wc8bOfIhobfVRNGEBVj5M0yttanuvzey6u1");
        corecontacts.setNativeTitle("zyN9eqdjrbzVwyyvL6JIPBgaMl97SriDcEhCdbtY1rCV3zbMDH");
        corecontacts.setEmailId("Ej4TlHUXyRzJPgXuoDNIFlZhWSZWCwKTvlIEft9TgFr5OstYd2");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1466582802840l));
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLastName("4ebu8ZxIetx1aojyWJEBXec8U980f4upC3nAqUTm1RIPN9zbiL");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeDescription("c57ScwutxiEg9ZTQsLvZzX8PH28ywGIiDNCPY1mmNWSGW4jipa");
        communicationtype.setCommTypeName("5nGcFn8Re5er0mMUr9g59kDKgRBZelK1f9jRZzp5riR3wiLFEB");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("T4UBIIeIkSXUABI4QH0ATsDaUrCDTZGP7OyOM0asUT3hsnIy3c");
        communicationgroup.setCommGroupDescription("oWQhkB3Jc9adqAayaNCA7BiADUzbxaRn6gv4tujwxuq8RP0mnr");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeDescription("8fLrXX55iNNvgIkIZQKrPAstQ32KV20bF9EJ0AC7F2waZcQvbK");
        communicationtype.setCommTypeName("a4LVIVVH5VFXtOkjyyJuxNhg545EWq3HeURQB1mhidfTIBj9tB");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("TbxmgDw5Zz");
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress2("1M9ggSGtQ0tDZBn2LgSNoHPqbTJrCBpHYRwYWrQAG0dM2JedSQ");
        address.setLongitude("hL7cp5L5s2SST7qrQ2hfdeseMtDnJCmBArXtpSo3wO152dvbyO");
        address.setZipcode("157Ee6");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("4JlTKJkP4pdWzZ2kBp6NPelmCHFVTmUSg8HlZU8NsqINExSpSr");
        addresstype.setAddressTypeIcon("McZnmvJ8d9RBPDzqQaz96K5mcEQsfAXftZ2E21FzQEj01chcIQ");
        addresstype.setAddressType("qMwkyITmKDsjvtjrLVs4rfcyLvFnEfJNVQhLRJXi7RdQZVTYK5");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Country country = new Country();
        country.setCountryCode1("SUS");
        country.setCapitalLatitude(2);
        country.setCurrencyCode("wLg");
        country.setIsoNumeric(191);
        country.setCapital("0C54C4W9TkqPnepruc2ZFoau9V9QMLM8");
        country.setCountryCode2("gnl");
        country.setCapitalLongitude(11);
        country.setCountryName("ZNhni5q0G9RFeRgceszC0Z5zZ6IxkPqpRv8S8hD2b5pnVe3vVv");
        country.setCurrencyName("nFASYm8ZkQXLh5NkczQbde3asnIzgoBYsGzemN5BILAUXQonKD");
        country.setCountryFlag("G3WfWLv157oKIurc4uv5m7hKVVGq5DBSaACpsQWWmRjLZEYBiQ");
        country.setCurrencySymbol("7csoqUQDHwSwU6U9esOCCQwI0mD3UUsI");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("KFJC9N0FKr8BrHb78mTc1XpalemHnVPRgDuSKfyJGdBUmz6W3p");
        state.setStateFlag("40YzBLMc7AQjYrFPuTsOmdrzMcsBxtnkfnrMZZDHMMcySqzgBf");
        state.setStateCapital("XqaxfXc8WOjByDe0p8UF2IBuqJAxOpIXht5ooL1YFCymZrMmVf");
        state.setStateCapitalLongitude(10);
        state.setStateCodeChar3("gN9kG4hpicAcJstI7Ro94PXSymCzYNwE");
        state.setStateCode(1);
        state.setStateCodeChar2("ZD4vbKeoPlVSPKtDKdVfBmHBw1mbWSnf");
        state.setStateCapitalLatitude(5);
        state.setStateDescription("giZqazeEKuhkBe6eLUdcBmlJmXxGfibKTycKVICwGLAbzniJ9A");
        state.setStateName("60HcD1LNpIZnlMexz3oopCTpiMh0p88NGuYMwrwD2Zmi7GQNmu");
        state.setStateFlag("FUvo55K4g8OUqqPbpM4idSPxwJS8yVLP70QeUJEl71ZvzdxMib");
        state.setStateCapital("jLgQOCRFKpmPWDuafgu3VMSrEViWtAZNwtpn2NtP8pUDxExPWc");
        state.setStateCapitalLongitude(6);
        state.setStateCodeChar3("OJqQQnCwUuCVEGwmduewnLAJHKvI9Itm");
        state.setStateCode(1);
        state.setStateCodeChar2("qxn1vOwwz7y4o0IhkxyMoNvhvC0c28dn");
        state.setStateCapitalLatitude(5);
        state.setStateDescription("NITtnVJiV1A6DuB46W76rotORwZoyIOZx9bjEpKepBYCuJjXFn");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(1);
        city.setCityLatitude(1);
        city.setCityLongitude(3);
        city.setCityLatitude(11);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("oz6XhnWSUGnjYg8RZyCMMyBNPCSMTNigxuYuqbrpZrxvO0gMfG");
        city.setCityCodeChar2("1NIasZIElhVWc6u1YPxQMfi6QK7AGfGz");
        city.setCityCode(2);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("wW5fZuPuMlRKbpQ0gvMZZw8YzJUJWmaEyndQ3cjuipft2SovcT");
        city.setCityName("kqOEfnubY9oguH5L6F8sPuW7fXeLDz2X3fWM6U2MpohvibwPPq");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddress2("HTTiGuGqkf7K6vu3uJ1cz8cUfmuGd62YT3zL1HuxVqVbGPV5K3");
        address.setLongitude("Fu5WCkoqoD2SQOvPEb6hYuENLXazDHVwAWnxvC0GI4sFcMMlCF");
        address.setZipcode("kanBCk");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("APGW9zP0zhO2TYushFqstkAHMP1ISmT7zWxxLwVMOpDv37Dz7w");
        address.setAddress3("3uDkgven7VnVuCYPlIgPKadUh2pgYitlXoWoJ4Vd7FSrroh9WI");
        address.setLatitude("bV5yVVzLwuoFdO0IRdnSbej1Dmkwipbzp7WB2o8pxvil1kaAUh");
        address.setAddressLabel("FndxKfZwFWe");
        address.setCityId((java.lang.String) CityTest._getPrimarykey());
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        Login login = new Login();
        login.setLoginId("qRFm8BxmAM9LuMRHoQx8RJ2K2iUeJ01F9KPYkSMgQF76TO8iuE");
        login.setIsAuthenticated(true);
        user.setUserId(null);
        login.setUser(user);
        login.setServerAuthText("uguX84truKyn90fU");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setFailedLoginAttempts(1);
        login.setServerAuthImage("6E4tQ9xmNdEdtGYvlncLebHEPOVcgSgb");
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setVersionId(1);
            login.setLoginId("Xyrs9WhKjiRezEgOe9MMQybT41MUn7jUXUYCUQNgacxJwsej7n");
            login.setServerAuthText("JHBoCPZFMdo1fvFv");
            login.setFailedLoginAttempts(11);
            login.setServerAuthImage("KerjQLjY0RNmu5OwB4iREbScyhehyYsH");
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "uudRqIZcinYPhAyufdfuoZ5wmsJIxh495XJy10pfnwPrFIiSlmzvvpJhbPIP8ulwgv7lr4F9P0cxsXHk57tNdk8twZkb93DS1NYkCyDIrOID0qQEtQCez7iAWZkBAYiIhibtFrjKbhyY5bWubkCp3KqaO9mVyGVBtKP1e5rjAVdZ8mas2FyvwNNL498ijJ0fycAQJwuo7"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "MbXXnl6YY3JtWCNJZYyIdlIdX3jwHn96f"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "DHEqYKSohoDmIsmqp"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 13));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
