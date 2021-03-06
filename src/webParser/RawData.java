package webParser;

public class RawData {
    private String date;
    private int roadNumber;
    private String road;
    private String fromTo;
    private int fromToNumber;
    private String roadName;
    private int roadNameNumber;
    private double distance;
    private double speed;
    private String color;
    private String status;
    private String[] writeOnCSV;
    
    public RawData() {
       
    }
    
    
    
    public RawData(String date, int roadNumber, String road, String fromTo, int fromToNumber, String roadName,
            int roadNameNumber, double distance, double speed, String color) {
        this.date = date;
        this.roadNumber = roadNumber;
        this.road = road;
        this.fromTo = fromTo;
        this.fromToNumber = fromToNumber;
        this.roadName = roadName;
        this.roadNameNumber = roadNameNumber;
        this.distance = distance;
        this.speed = speed;
        this.color = color;
        setStatus(color);
        setWriteOnCSV(date, roadNumber, road, fromTo, roadName, distance, speed, color);
    }

    public RawData(int roadNumber, String road, String fromTo, String roadName, double distance, double speed,
            String color) {
        this.roadNumber = roadNumber;
        this.road = road;
        this.fromTo = fromTo;
        this.roadName = roadName;
        this.distance = distance;
        this.speed = speed;
        this.color = color;
        setStatus(color);
    }
    
    

    public int getFromToNumber() {
        return fromToNumber;
    }

    public void setFromToNumber(int fromToNumber) {
        this.fromToNumber = fromToNumber;
    }

    public int getRoadNameNumber() {
        return roadNameNumber;
    }

    public void setRoadNameNumber(int roadNameNumber) {
        this.roadNameNumber = roadNameNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRoadNumber() {
        return roadNumber;
    }

    public void setRoadNumber(int roadNumber) {
        this.roadNumber = roadNumber;
    }
    
    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getFromTo() {
        return fromTo;
    }

    public void setFromTo(String fromTo) {
        this.fromTo = fromTo;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String color) {
        if (color.equals("green")) {
            this.status = "??????";
        } else if (color.equals("red")) {
            this.status = "??????";
        } else if (color.equals("yellow")) {
            this.status = "??????";
        } else if (color.equals("darkgray")) {
            this.status = "";
        } else {
            this.status = "????????????";
        }
    }

    public String[] getWriteOnCSV() {
        return writeOnCSV;
    }

    public void setWriteOnCSV(String date, int roadNumber, String road, String fromTo, String roadName,
            double distance, double speed, String color) {
        this.writeOnCSV = new String[9];
        writeOnCSV[0] = date;
        writeOnCSV[1] = "" + roadNumber;
        writeOnCSV[2] = road;
        writeOnCSV[3] = fromTo;
        writeOnCSV[4] = roadName;
        writeOnCSV[5] = "" + distance;
        writeOnCSV[6] = "" + speed;
        writeOnCSV[7] = color;
        writeOnCSV[8] = this.status;
    }
    
    

}
