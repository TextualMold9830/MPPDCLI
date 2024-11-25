package textualmold9830.cli.variables;

public class IncrementedVariable implements Variable {
    public int currentValue = 0;
    public int incrementBy = 1;
    @Override
    public String getValue() {
        String value = String.valueOf(currentValue);
        currentValue += incrementBy;
        return value;
    }

    public IncrementedVariable() {
    }
}
