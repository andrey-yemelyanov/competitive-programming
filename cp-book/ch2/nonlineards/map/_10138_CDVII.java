import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10138 CDVII
Problem url: https://uva.onlinejudge.org/external/101/10138.pdf
Author: Andrey Yemelyanov
*/
public class _10138_CDVII {
  static final String ENTER = "enter";
  static final String EXIT = "exit";
  static class PhotoRecord{
    public String licensePlate;
    public Calendar timestamp;
    public String type;
    public int pos;
    public String toString(){
      return licensePlate + " " + timestamp.getTime() + " " + type + " " + pos;
    }
    public PhotoRecord(String licensePlate, Calendar timestamp, String type, int pos){
      this.licensePlate = licensePlate;
      this.timestamp = timestamp;
      this.type = type;
      this.pos = pos;
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    s.nextLine();
    for(int t = 0; t < nTests; t++){
      int[] toll = new int[24];
      for(int i = 0; i < toll.length; i++){
        toll[i] = s.nextInt();
      }
      s.nextLine();
      List<PhotoRecord> records = new ArrayList<>();
      while(s.hasNext()){
        String line = s.nextLine();
        if(line.isEmpty()) break;
        String[] record = line.split("\\s+");
        Calendar timestamp = Calendar.getInstance();
        String[] date = record[1].split(":");
        timestamp.set(Calendar.YEAR, 2017);
        timestamp.set(Calendar.MONTH, Integer.parseInt(date[0]) - 1);
        timestamp.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[1]));
        timestamp.set(Calendar.HOUR_OF_DAY, Integer.parseInt(date[2]));
        timestamp.set(Calendar.MINUTE, Integer.parseInt(date[3]));
        timestamp.set(Calendar.SECOND, 0);
        records.add(new PhotoRecord(record[0], timestamp, record[2], Integer.parseInt(record[3])));
      }
      Map<String, Integer> bills = getBills(records, toll);
      for(String licensePlate : bills.keySet()){
        System.out.printf("%s $%.2f\n", licensePlate, bills.get(licensePlate) / 100.0);
      }
      if(t < nTests - 1) System.out.println();
    }
  }

  static Map<String, Integer> getBills(List<PhotoRecord> records, int[] toll){
    Map<String, Integer> bills = new TreeMap<>();
    Collections.sort(records, new Comparator<PhotoRecord>(){
      @Override
      public int compare(PhotoRecord rec1, PhotoRecord rec2){
        if(!rec1.licensePlate.equals(rec2.licensePlate)) return rec1.licensePlate.compareTo(rec2.licensePlate);
        return rec1.timestamp.compareTo(rec2.timestamp);
      }
    });
    //System.out.println(records);
    final int accountCharge = 200;
    PhotoRecord prevRecord = records.get(0);
    for(int i = 1; i < records.size(); i++){
      PhotoRecord record = records.get(i);
      if(prevRecord.licensePlate.equals(record.licensePlate) && prevRecord.type.equals(ENTER) && record.type.equals(EXIT)){
        if(!bills.containsKey(record.licensePlate)){
          bills.put(record.licensePlate, accountCharge);
        }
        bills.put(record.licensePlate, bills.get(record.licensePlate) + getTollForTrip(prevRecord, record, toll));
      }
      prevRecord = record;
    }
    return bills;
  }

  static int getTollForTrip(PhotoRecord enterRecord, PhotoRecord exitRecord, int[] toll){
    final int feePerTrip = 100;
    int tollAtEntry = toll[enterRecord.timestamp.get(Calendar.HOUR_OF_DAY)];
    int distanceTravelled = abs(enterRecord.pos - exitRecord.pos);
    return tollAtEntry * distanceTravelled + feePerTrip;
  }
}
