package kr.co.team.res.common.utill;

import kr.co.team.res.domain.vo.MemberVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateFormatHandler {
    public MemberVO ParseDate(LocalDate date) throws ParseException {
        String ymd = date.toString();
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date to = fm.parse(ymd);
        MemberVO vo = new MemberVO();
        vo.setYear(String.format("%%ty(2y): %ty, %%tY(4y): %tY", to,to));;
        vo.setMonth(String.format("%%tm(02M): %tm", to));;
        vo.setDay(String.format("%%td(02d): %td, %%te(d): %te", to,to));;
        return vo;


    }
}
