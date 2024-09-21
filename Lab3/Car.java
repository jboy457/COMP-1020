  class Car {
    private String make;
    private String model;
    private int year;
    private double lPer100Km;
    private Driver driver;
    
    public Car(String make, String model, int year, double lPer100Km) {
      this.make = make;
      this.model = model;
      this.year = year;
      this.lPer100Km = lPer100Km;
      this.driver = null;
    }
    
    public String getMake() {
      return make;
    }
  
    public int getYear() {
      return year;
    }
  
    public double getLPer100Km() {
      return lPer100Km;
    }
    
    public String toString() {
      return String.format("%s %s (%d) %.1fl/100km", make, model, year, lPer100Km);
    }

    public void setDriver (Driver driver){
        this.driver = driver;
    }
  }
  