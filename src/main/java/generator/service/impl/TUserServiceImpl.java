package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.entity.TUser;
import generator.service.TUserService;
import generator.mapper.TUserMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser>
    implements TUserService{

}




