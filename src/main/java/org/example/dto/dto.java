package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.util.util;

import java.util.Map;

@Getter
@Setter
public class dto {
    public int id;
    public String regDate;

    public dto() {
        this(0);
    }

    public dto(int id) {
        this(id, util.getNowDateStr());
    }

    public dto(int id, String regDate) {
        this.id = id;
        this.regDate = regDate;
    }

    public dto(Map<String, Object> row) {
        this((int) row.get("id"), (String) row.get("regDate"));
    }
}
