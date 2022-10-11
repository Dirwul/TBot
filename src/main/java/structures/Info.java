package structures;

public class Info {

    public static final double EPS = 1e-9;

    private CalculationType calcType;

    private SectionType sectionType;

    private double stepValue;

    private int stepQuantity;

    private State state;

    public Info() {
        this.calcType = null;
        this.sectionType = null;
        this.stepValue = 0.0;
        this.stepQuantity = 0;
        this.state = State.NONE;
    }

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

    public void setState(State state) {
        this.state = state;
    }

    public double getStepValue() {
        return stepValue;
    }

    public int getStepQuantity() {
        return stepQuantity;
    }

    public State getState() {
        return state;
    }
}
