

package cn.fibo.cdp.modules.oss.service.impl;

import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;
import cn.fibo.cdp.modules.oss.dao.SysOssDao;
import cn.fibo.cdp.modules.oss.entity.SysOssEntity;
import cn.fibo.cdp.modules.oss.service.SysOssService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysOssService")
public class SysOssServiceImpl extends ServiceImpl<SysOssDao, SysOssEntity> implements SysOssService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		IPage<SysOssEntity> page = this.page(
			new Query<SysOssEntity>().getPage(params)
		);

		return new PageUtils(page);
	}

}
