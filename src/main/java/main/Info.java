package main;

public class Info {

    final double EPS = 1e-9;

    private CalculationType calcType = null;

    private SectionType sectionType = null;

    private double stepValue = 0.0;

    private int nSteps = 0;

    public Info() {}

    public Info(CalculationType type) {
        this.calcType = type;
    }

    public Info (SectionType type) {
        this.sectionType = type;
    }

    public Info (double stepValue) {
        this.stepValue = stepValue;
    }

    public Info (int nSteps) {
        this.nSteps = nSteps;
    }

    public CalculationType getCalcType() {
        return calcType;
    }

    public SectionType getSectionType() {
        return sectionType;
    }

    void set(Info second) {
        if (second.calcType != null) {
            this.calcType = second.calcType;
        }
        if (second.sectionType != null) {
            this.sectionType = second.sectionType;
        }
        if (second.stepValue > EPS) {
            this.stepValue = second.stepValue;
        }
        if (second.nSteps > 0) {
            this.nSteps = second.nSteps;
        }
    }

    boolean isCalcTypeOk() {
        return calcType != null;
    }

    boolean isSectionTypeOk() {
        return sectionType != null;
    }

    public boolean isSectionValueOk() {
        return sectionType == SectionType.BY_STEPS && nSteps > 0
                || sectionType == SectionType.BY_STEP_VALUE && stepValue > EPS;
    }

    final Info NullInfo() {
        return new Info();
    }
}
