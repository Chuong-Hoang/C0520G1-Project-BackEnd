package sprint_1.dto;

/**
 * class BookedChartDTO ( statistic chart by year)
 * <p>
 * Version 1.0
 * <p>
 * Date: 24/11/2020
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 24/11/2020        Nguyễn Tiến Hải            class
 */

public class BookedChartDTO {
    private String year;
    private double effectiveMonth1;
    private double effectiveMonth2;
    private double effectiveMonth3;
    private double effectiveMonth4;
    private double effectiveMonth5;
    private double effectiveMonth6;
    private double effectiveMonth7;
    private double effectiveMonth8;
    private double effectiveMonth9;
    private double effectiveMonth10;
    private double effectiveMonth11;
    private double effectiveMonth12;

    public BookedChartDTO(String year, double effectiveMonth1, double effectiveMonth2, double effectiveMonth3, double effectiveMonth4, double effectiveMonth5, double effectiveMonth6, double effectiveMonth7, double effectiveMonth8, double effectiveMonth9, double effectiveMonth10, double effectiveMonth11, double effectiveMonth12) {
        this.year = year;
        this.effectiveMonth1 = effectiveMonth1;
        this.effectiveMonth2 = effectiveMonth2;
        this.effectiveMonth3 = effectiveMonth3;
        this.effectiveMonth4 = effectiveMonth4;
        this.effectiveMonth5 = effectiveMonth5;
        this.effectiveMonth6 = effectiveMonth6;
        this.effectiveMonth7 = effectiveMonth7;
        this.effectiveMonth8 = effectiveMonth8;
        this.effectiveMonth9 = effectiveMonth9;
        this.effectiveMonth10 = effectiveMonth10;
        this.effectiveMonth11 = effectiveMonth11;
        this.effectiveMonth12 = effectiveMonth12;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getEffectiveMonth1() {
        return effectiveMonth1;
    }

    public void setEffectiveMonth1(double effectiveMonth1) {
        this.effectiveMonth1 = effectiveMonth1;
    }

    public double getEffectiveMonth2() {
        return effectiveMonth2;
    }

    public void setEffectiveMonth2(double effectiveMonth2) {
        this.effectiveMonth2 = effectiveMonth2;
    }

    public double getEffectiveMonth3() {
        return effectiveMonth3;
    }

    public void setEffectiveMonth3(double effectiveMonth3) {
        this.effectiveMonth3 = effectiveMonth3;
    }

    public double getEffectiveMonth4() {
        return effectiveMonth4;
    }

    public void setEffectiveMonth4(double effectiveMonth4) {
        this.effectiveMonth4 = effectiveMonth4;
    }

    public double getEffectiveMonth5() {
        return effectiveMonth5;
    }

    public void setEffectiveMonth5(double effectiveMonth5) {
        this.effectiveMonth5 = effectiveMonth5;
    }

    public double getEffectiveMonth6() {
        return effectiveMonth6;
    }

    public void setEffectiveMonth6(double effectiveMonth6) {
        this.effectiveMonth6 = effectiveMonth6;
    }

    public double getEffectiveMonth7() {
        return effectiveMonth7;
    }

    public void setEffectiveMonth7(double effectiveMonth7) {
        this.effectiveMonth7 = effectiveMonth7;
    }

    public double getEffectiveMonth8() {
        return effectiveMonth8;
    }

    public void setEffectiveMonth8(double effectiveMonth8) {
        this.effectiveMonth8 = effectiveMonth8;
    }

    public double getEffectiveMonth9() {
        return effectiveMonth9;
    }

    public void setEffectiveMonth9(double effectiveMonth9) {
        this.effectiveMonth9 = effectiveMonth9;
    }

    public double getEffectiveMonth10() {
        return effectiveMonth10;
    }

    public void setEffectiveMonth10(double effectiveMonth10) {
        this.effectiveMonth10 = effectiveMonth10;
    }

    public double getEffectiveMonth11() {
        return effectiveMonth11;
    }

    public void setEffectiveMonth11(double effectiveMonth11) {
        this.effectiveMonth11 = effectiveMonth11;
    }

    public double getEffectiveMonth12() {
        return effectiveMonth12;
    }

    public void setEffectiveMonth12(double effectiveMonth12) {
        this.effectiveMonth12 = effectiveMonth12;
    }
}
