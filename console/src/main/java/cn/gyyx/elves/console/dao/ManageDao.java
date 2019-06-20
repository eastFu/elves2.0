package cn.gyyx.elves.console.dao;

import cn.gyyx.elves.console.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface ManageDao {

    public User valUserCenter(User user) throws Exception;

	public void reCordUserLogin(User user) throws Exception;
    
}
