package consumerTest.Payloads;

import consumerTest.Pojos.*;
import consumerTest.utils.DeviceTypes;
import consumerTest.utils.RandomDataGenerator;
import consumerTest.utils.RandomDataTypeName;
import consumerTest.utils.TariffTypes;

import java.util.Collections;
import java.util.List;

public class Payloads {

    static String oldAccountNumber;
    static String newAccountNumber;
    static String rootSpace_Id;
    static String id;
    static String smartPlugId;
    static String AcPlugId;

    public static void setRootSpaceID(String id) {
        rootSpace_Id = id;
    }

    public static void setDeviceID(String device_Id) {
        id = device_Id;
    }

    public static void setSmartPlugID(String smart_ID) {
        smartPlugId = smart_ID;
    }

    public static void setAcID(String AcId) {
        AcPlugId = AcId;
    }

    public static AddAccountNumber getCreateAccountNumberPayloadFromPojo(){
        oldAccountNumber = String.valueOf(RandomDataGenerator.getRandomNumber(8));
        return AddAccountNumber
                .builder()
                .label(RandomDataGenerator.getRandomDataFor(RandomDataTypeName.LOREM))
                .accountNumber(String.valueOf(Integer.parseInt(oldAccountNumber)))
                .tariffCode(TariffTypes.D1)
                .build();
    }

    public static UpdateAccountNumber getUpdateAccountNumberPayloadFromPojo(){
        newAccountNumber = String.valueOf(RandomDataGenerator.getRandomNumber(8));
        return UpdateAccountNumber
                .builder()
                .oldAccountNumber(String.valueOf(Integer.parseInt(oldAccountNumber)))
                .label(RandomDataGenerator.getRandomDataFor(RandomDataTypeName.LOREM))
                .tariffCode(TariffTypes.D1)
                .updatedAccountNumber(String.valueOf(Integer.parseInt(newAccountNumber)))
                .build();
    }

    public static AddBillingSpace getCreateBillingSpacePayloadFromPojo(){
        return AddBillingSpace
                .builder()
                .spaceClusterLabel(RandomDataGenerator.getRandomDataFor(RandomDataTypeName.LOREM))
                .rootSpaceName(RandomDataGenerator.getRandomDataFor(RandomDataTypeName.LOREM))
                .accountNumber(String.valueOf(Integer.parseInt(newAccountNumber)))
                .build();
    }

    public static UpdateBillingSpace getUpdateBillingSpacePayloadFromPojo(){
        return UpdateBillingSpace
                .builder()
                .label(RandomDataGenerator.getRandomDataFor(RandomDataTypeName.LOREM))
                .accountNumber(String.valueOf(Integer.parseInt(newAccountNumber)))
                .build();

    }

    public static SubSpaces getSubSpacesPayloadFromPojo(String rootSpace_Id){
        return SubSpaces
                .builder()
                .parentSpaceId(rootSpace_Id)
                .spaceName(RandomDataGenerator.getRandomDataFor(RandomDataTypeName.LOREM))
                .build();

    }

    public static UpdateSpace getUpdateSpacePayloadFromPojo(){
        return UpdateSpace
                .builder()
                .spaceName(RandomDataGenerator.getRandomDataFor(RandomDataTypeName.LOREM))
                .build();

    }

    public static RegisterDevices getRegisterDevicePayloadFromPojo(){
        RegisterDevices.registerDevice device = RegisterDevices.registerDevice.builder()
                .name(RandomDataGenerator.getRandomDataFor(RandomDataTypeName.LOREM))
                .deviceType(DeviceTypes.AirConditioner)
                .deviceCategory(RandomDataGenerator.getRandomDataFor(RandomDataTypeName.LOREM))
                .brand(RandomDataGenerator.getRandomDataFor(RandomDataTypeName.LOREM))
                .modelNumber(RandomDataGenerator.getRandomDataFor(RandomDataTypeName.MODELNUMBER))
                .serialKey(RandomDataGenerator.getRandomNumber(2))
                .powerUsageInWatt(10)
                .build();

        return RegisterDevices.builder()
                .registeringDevices(List.of(device))
                .build();

    }

    public static UpdateDevices getUpdateDevicePayloadFromPojo(String id){
        return UpdateDevices
                .builder()
                .id(id)
                .name(RandomDataGenerator.getRandomDataFor(RandomDataTypeName.LOREM))
                .deviceType(DeviceTypes.AirConditioner)
                .deviceCategory(RandomDataGenerator.getRandomDataFor(RandomDataTypeName.LOREM))
                .brand(RandomDataGenerator.getRandomDataFor(RandomDataTypeName.LOREM))
                .modelNumber(RandomDataGenerator.getRandomDataFor(RandomDataTypeName.MODELNUMBER))
                .serialKey(RandomDataGenerator.getRandomNumber(2))
                .powerUsageInWatt(20)
                .build();
    }

    public static RegisterSmartPlug getRegisterSmartPlugPayloadFromPojo(String id){
        RegisterSmartPlug.registerPlug plug = RegisterSmartPlug.registerPlug.builder()
                .name(RandomDataGenerator.getRandomDataFor(RandomDataTypeName.LOREM))
                .serialKey("OVS-SMART-PLUG-1008")
                .linkedDeviceId(id)
                .build();

        return RegisterSmartPlug.builder()
                .registeringSmartPlugs(List.of(plug))
                .build();

    }

    public static UpdateSmartPlug getUpdateSmartPlugPayloadFromPojo(String smartPlugId){
        return UpdateSmartPlug
                .builder()
                .name(RandomDataGenerator.getRandomDataFor(RandomDataTypeName.LOREM))
                .linkedPowerConsumerId(smartPlugId)
                .build();
    }

    public static UnlinkDevice getUnlinkDevicePayloadFromPojo(String id){
        return UnlinkDevice
                .builder()
                .linkedPowerConsumerId(id)
                .excludedSemiAutomatedSchedules(Collections.emptyList())
                .build();

    }

    public static RegisterAcController getRegisterAcControllerPayloadFromPojo(String id){
        RegisterAcController.registerAcCon AC = RegisterAcController.registerAcCon.builder()
                .name(RandomDataGenerator.getRandomDataFor(RandomDataTypeName.LOREM))
                .serialKey("OVS-AC-CONTROLLER-51")
                .linkedAcId(id)
                .build();

        return RegisterAcController.builder()
                .registeringAcControllers(List.of(AC))
                .build();

    }

    public static UpdateAcController getUpdateAcControllerPlugPayloadFromPojo(String id){
        return UpdateAcController
                .builder()
                .name(RandomDataGenerator.getRandomDataFor(RandomDataTypeName.LOREM))
                .linkedAcId(id)
                .build();
    }


    public static Schedule getSchedulePayloadFromPojo(String id){
        return Schedule
                .builder()
                .title(RandomDataGenerator.getRandomDataFor(RandomDataTypeName.LOREM))
                .schedulingDate("2024-02-10T00:00:00.470+00:00")
                .schedulingPeriods(Collections.singletonList(
                        new SchedulingPeriod("2024-02-10T14:00:00.470+00:00", "2024-02-10T16:15:00.470+00:00")
                ))
                .repetitionMode("DAILY")
                .expireDate("2024-02-20T00:00:00.470+00:00")
                .selectedPowerConsumerIdentities(Collections.singletonList(id))
                .isDraft(false)
                .build();

    }


    }


