package SeoulBomo.SeoulBomoBe.domain.childCenterInfo.model;

public enum ServiceType {
    COMMON("일반"),
    PARTTIME("시간제보육"),
    DISABLED("장애아통합"),
    OVERTIME("야간연장"),
    HOLIDAY("휴일보육");

    private String detail;

    ServiceType(String detail) {
        this.detail = detail;
    }

    public static ServiceType getType(String name) {
        for(ServiceType p : values()){
            if(p.detail.equals(name)){
                return p;
            }
        }
        return null;
    }
}