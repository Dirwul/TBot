package structures;

public class Info {

    public static final double EPS = 1e-9;

    private CalculationType calcType = null;

    private SectionType sectionType = null;

    private double stepValue = 0.0;

    private int stepQuantity = 0;


    public boolean isOk() {
        return calcType != null &&
                (sectionType == SectionType.BY_STEP_QUANTITY && stepQuantity > 0 ||
                sectionType == SectionType.BY_STEP_VALUE && stepValue > EPS);
    }

    public void setCalcType(CalculationType calcType) {
        this.calcType = calcType;
    }

    public void setSectionType(SectionType sectionType) {
        this.sectionType = sectionType;
    }

    public void setStepValue(double stepValue) {
        this.stepValue = stepValue;
    }

    public void setStepQuantity(int stepQuantity) {
        this.stepQuantity = stepQuantity;
    }

    public double getStepValue() {
        return stepValue;
    }

    public int getStepQuantity() {
        return stepQuantity;
    }
}
