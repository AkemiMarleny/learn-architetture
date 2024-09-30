package co.idesoft.architetture.common;

import org.springframework.util.DigestUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Sum {
   
    public static String fromContent(String content){
        return DigestUtils.md5DigestAsHex(content.getBytes());
    }

    
}
