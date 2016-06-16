#hibernate
## 缓存
1. 一级缓存：session级别的缓存
2. 二级缓存：sessionFactory级别的缓存，一般使用第三方的缓存提供方案
1.  整合ehcache
1.   在hibernate的配置文件中添加以下配置
	`
	<!-- 开启二级缓存：默认为开启的 -->
	<property name="hibernate.cache.use_second_level_cache">true</property>
	<!-- 指定二级缓存的提供者 -->
	<property 		name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegionFactory</property>
	`
2.   添加ehcache的配置文件：可以在hibernate解压之后etc中获取到
3.   指定需要缓存的实体类：<br>
              在hibernate的配置文件中使用<class-cache 节点指定缓存的实体类<br>
              或则在实体类映射文件中使用<cache 节点指定(在class里面并且在id之前)
4.   通过对get方法缓存数据<br>
              查询缓存：1.在hibernate的配置文件中开启查询缓存<br> 2.在query调用查询之前调用setCacheable开启缓存在