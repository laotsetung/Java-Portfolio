package lao.hotel.extras;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateManipulation {

    public String DateToString(Date data){

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(data);

        String ano = String.valueOf(data).substring(String.valueOf(data).length()-4);
        String mes = String.valueOf(data.getMonth());
        String dia = String.valueOf(data.getDate());

        String dataStrFinal = ano + "-" + mes + "-" + dia;

        System.out.println("Date to String : " + data + " / " + dataStrFinal);
        return dataStrFinal;
    }

    public Date StringDoDate(String data){
        String ano, mes, dia;
        String[] novaData = data.split("-");

        ano = novaData[0];
        mes = novaData[1];
        dia = novaData[2];

        Date dataRet = new Date();
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, Integer.parseInt(ano));
        cal.set(Calendar.MONTH, Integer.parseInt(mes));
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));

        dataRet = cal.getTime();

        System.out.println("String to Data : " + data + " / " + dataRet);
        return dataRet;
    }
}
