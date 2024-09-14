import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.json.*;
import java.util.*;
import com.alibaba.fastjson.*;
public class ApartWordText {
    public static void ApartWold(){
        String str="横眉冷读对千夫指，俯首甘为孺子牛";
        Map<String, Integer> map = new HashMap<>();
        ToAnalysis.parse(str).forEach(item -> {
            //没有则赋初始值，有则+1
            if (map.get(item.getName()) == null){
                map.put(item.getName(),1);
            }else {
                map.put(item.getName(),map.get(item.getName())+1);
            }
        });
        System.out.println("map="+JSON.toJSONString(map));
    }
}
