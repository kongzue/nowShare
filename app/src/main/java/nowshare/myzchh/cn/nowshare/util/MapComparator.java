package nowshare.myzchh.cn.nowshare.util;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

/**
 * Created by chao on 2015/5/21.
 */
public class MapComparator implements Comparator<Map<String, Object>> {
    public int compare(Map<String, Object> o1, Map<String, Object> o2) {
        // TODO Auto-generated method stub
        String b1 = (String)o2.get("bpubtime");
        String b2 = (String)o1.get("bpubtime");
        if (b2 != null) {
            return b1.compareTo(b2);
        }
        return 0;
    }

}
