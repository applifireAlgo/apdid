package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.Address;
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
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws Exception {
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("ngkOp9lBCQx02eMqDrg1uMKb8gJEvG4oShujXR8f3tHZ696KcL");
        addresstype.setAddressTypeIcon("0XPSZH8dOTb19LR0M7Y9RiGUsAsmDMSFStxy8JylbyWG9y2J63");
        addresstype.setAddressType("r9pC5LUeGK1ims94f96XMzQynkZl33uX5dguJ9rD9tAUHsBaon");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Country country = new Country();
        country.setCountryCode1("oeF");
        country.setCapitalLatitude(1);
        country.setCurrencyCode("6e3");
        country.setIsoNumeric(388);
        country.setCapital("UFfmeRw9KvgsbhR5kZT6JU5cDA1eObmu");
        country.setCountryCode2("A1Z");
        country.setCapitalLongitude(1);
        country.setCountryName("wywC4HLQ0XK6hA61gjqzRzVTxLMgUbGUtbbiEstq7alWeamshX");
        country.setCurrencyName("t5x3Wk4RiENU30zmnDFw4zh7PG1dPoO6jZNAF2RtyniluMYF4S");
        country.setCountryFlag("JPePoycmXWQc3gQjOd0jQ5e4AVDE757N7gPuTJdDyI1nJazvDF");
        country.setCurrencySymbol("vP8zHXVlUm0s1qmSqXiwZsToWPDqES6A");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("ECGVXVCsjxvEbtXiwynrkREwoIACQHo7GZ7T5ZmRCibRuwgJlc");
        state.setStateFlag("oOSJuNlqwuBkUQLsb1BbzJbNuQ2L6IIkGXqEOh0sNktBkAz75n");
        state.setStateCapital("DVqpZ6k0yr6Q5qIkC5YBf57qTSnPYDPqeGUAeWL9eZw2wEr7TN");
        state.setStateCapitalLongitude(2);
        state.setStateCodeChar3("1Z9bDUX9ueum0paTqvOFWzAhb1QuTvMM");
        state.setStateCode(2);
        state.setStateCodeChar2("8qF6VEAG5vKWkIiiCx6rEZOp4enbtR6A");
        state.setStateCapitalLatitude(3);
        state.setStateDescription("03F59cs6pbokD8EETLeV76GzDuP5KTMaTb2mi8AfLWShWyf4WU");
        state.setStateName("18VWp9r2SYkkeVKGPzQARYdpCGbCw4zZoKw83tZZYSjEffaMmc");
        state.setStateFlag("KuLw2Xg2CNN2HwNXiZ3yjRMR9LQJpIJXwFXXvDQt7RnlB4GQeB");
        state.setStateCapital("CI6Z7TayDbQ35SThuMciHD9cfCTF22iJs05pNBdTF6KTNdELHK");
        state.setStateCapitalLongitude(1);
        state.setStateCodeChar3("GB1MUmGh6zJOWFqZq5LfhRIJNQvUYlDx");
        state.setStateCode(1);
        state.setStateCodeChar2("UjEkmbrFX1LvlyNJgof32Qq2AHmrz2tr");
        state.setStateCapitalLatitude(3);
        state.setStateDescription("YeQGK9CZe1uIFmsp6qkk3WQTYlQa5UYBJ9JSMIYbYDYpeIWnzV");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(3);
        city.setCityLatitude(2);
        city.setCityLongitude(10);
        city.setCityLatitude(3);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("2SgLej467DbjPrn9gotQHWrSs2mQjhKMAYj7zMluTtGxMEOdph");
        city.setCityCodeChar2("ybLaimZ1KGFxnXKBnQRL83umqqk438zp");
        city.setCityCode(2);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("HsbUpGRyUYSpg2HUxBvpGNIT5Hfmh6PuRUKzbsvm1Ek8BdpVzS");
        city.setCityName("z75acni66kFumqoWjSIq6cFN8qpGG2acEW1eD2MRBIgvIGjomZ");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        Address address = new Address();
        address.setAddress2("3Yq3xIlejXQ2h5xKuQBFiZzvStg4HMqpSFKeiobZbMtgjDcGKn");
        address.setLongitude("3Kg4vZ6PE2tqw5gDZ0M1M20bK0RvXxZEew9q9LLj5MtycpwBfT");
        address.setZipcode("nw5XHm");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("cdhrPzgL25RtEquqchGeRlOK7ihiCeh80iXpjxo5wv39E0AYfN");
        address.setAddress3("lDjGnZrdMWHl1mHZVUYxEklyNYZvgqY8PRSb5zb0DmFVIGdcbP");
        address.setLatitude("OKOYQgIOFKW4VpeG4wdtbzrmhjChowHuSp9KVy5Df6otImJUEV");
        address.setAddressLabel("iWNmok65u4N");
        address.setCityId((java.lang.String) CityTest._getPrimarykey());
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

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
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setVersionId(1);
            address.setAddress2("2kWQP2twyNGUTszuMLXCjiqvVRHsCvsIwBzSBn9tfLdNSyWbel");
            address.setLongitude("41OKhg3RKwjEXa0blEY2YGAqfbHP9EA1nvpAYs97zwCmJpjlTR");
            address.setZipcode("mjNFGE");
            address.setAddress1("QdQi9izBnpcIFkIofqjYOzYPS2k5P9HsM5eCLGQ7Wr9GnWjERt");
            address.setAddress3("4Ui3cH0UdlQnAZBhgsXd2Wu7na7hbmiyfvfqoUp9VpTzCl3NPv");
            address.setLatitude("nBq7V0w75YCFzDAU8ulSMrcHPlU3acOvSHKwCmx8zVHlnmtkM2");
            address.setAddressLabel("OrgWeMmoXDd");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "9nJpaLiwu6lP"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "JIgx7cK1vAJq0DUceIqqpYupgFJV4niTr5Ge0BSDKy4z4tzUzKOj2L1KL"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "cRZUMgbd9xlsZPcN1S0W4BpdYclxBnMS3H9mQ1c0IWekmuOKwDZOYT5wt"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "bpNIY0V2VlwPjE3IpgbDmXd1iNVr3XmtLniX55Udv91AK2OYMjTqYuJi7"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "6jQAnVp"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "gTMuRgfvtFyZPjCnfhwg6agM8o3M7j1xlW5qpPevDtzqMze8B5sHLtQt1Jz3rVrQ7"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "s5LwaPkfzCO2ZMgKOIZ4vy82azkYgzuu9l9byENBDv38s5gYaDUecBqCSCFPdfI7Y"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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
