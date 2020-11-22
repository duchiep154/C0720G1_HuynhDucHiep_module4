package service;

import org.springframework.stereotype.Service;

@Service
public class TranslateImp implements Translate{
    String[] english = {"today", "is", "monday", "tomorrow", "tuesday","Wednesday","Thursday","Friday", "weak","saturday","Sunday", "sleep"};
    String[] vietnamese = {"Hôm Nay", "Là", "Thứ Hai", "Ngày Mai", "Thứ 3","Thứ 4","thứ 5","thứ 6", "làm việc","thứ 7","Chủ Nhật", "ngủ"};
    @Override
    public String translate(String word) {
        if (word.equals("")) {
            return "vui lòng ko để trống";
        }
        for (int i = 0; i < english.length; i++) {
            if (word.equals(english[i])) {
                return word + " có nghĩa là : " + vietnamese[i];
            }
        }
        return word + " không có trong danh mục từ điển , chờ xí để cập nhật sau";
    }
}
