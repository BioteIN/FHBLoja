
package controle;

import java.util.Date;
import java.util.GregorianCalendar;

public class DiferencaData {
    public static int dataDif(java.util.Date dataLow,java.util.Date dataHigh){
        GregorianCalendar endTime=new GregorianCalendar();
        GregorianCalendar curTime=new GregorianCalendar();
        GregorianCalendar baseTime=new GregorianCalendar();
        GregorianCalendar startTime=new GregorianCalendar();
        startTime.setTime(dataLow);
        endTime.setTime(dataHigh);
        int dif_multiplier=1;
        if(dataLow.compareTo(dataHigh)<0){
            baseTime.setTime(dataHigh);
            curTime.setTime(dataLow);
            dif_multiplier=1;
        }else{
            baseTime.setTime(dataLow);
            curTime.setTime(dataHigh);
            dif_multiplier=-1;
        }
        int result_years=0;
        int result_months=0;
        int result_days=0;
        while(curTime.get(GregorianCalendar.YEAR)<baseTime.get(GregorianCalendar.YEAR)||curTime.get(GregorianCalendar.MONTH)<baseTime.get(GregorianCalendar.MONTH)){
            int max_day=curTime.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
            result_months+=max_day;
            curTime.add(GregorianCalendar.MONTH,1);
        }
        result_months=result_months*dif_multiplier;
        
        result_days+=(endTime.get(GregorianCalendar.DAY_OF_MONTH)-startTime.get(GregorianCalendar.DAY_OF_MONTH));
        return result_years+result_months+result_days;
    }
}
