package cn.gyyx.elves.console.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AppVersionDao {

    List<Map<String,Object>> queryListForPage(Map<String,Object> map);

}
