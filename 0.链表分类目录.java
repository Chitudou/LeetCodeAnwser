1836题
日期：2022年1月27号

链表54道，剑指offer18道，面试题12道

一、链表反转-8道
	24.两两交换链表中的节点(再看看)
	25.K个一组翻转链表(再看看)
	61.旋转链表(再看看)
	206.反转链表(10min)
	92.反转链表II(10min)
	143.重排链表(10min)
	234.回文链表(10min)
	2130.链表最大孪生和(10min)
二、链表删除-8道迭代，递归不会
	19.删除链表倒数第N个结点(10min)
	83.删除链表中的重复元素(10min)
	82.删除链表中的重复元素II(再看看)
	203.移除链表元素(10min)
	237.删除链表中的节点(10min)
	1171.从链表中删去总和值为零的连续节点(不会，前缀和)
	1721.交换链表中的节点(10min)
	2095.删除链表的中间节点(10min)
三、合并链表-5道
	21.两个排序链表合并(10min迭代，递归再看看)
	23.多个排序链表合并(再看看)
	328.奇偶链表(10min)
	1669.合并两个链表(10min)
	2181. 合并零之间的节点(10min)
四、链表求环-2道
	141.环形链表(10min)
	142.环形链表II(10min)
五、两个链表的交点-1道
	160.相交链表(10min)

七、设计数据结构-11道
	146.LRU缓存
	355.设计推特
	460.LFU缓存
	622.设计循环队列
	641.设计循环双端队列	
	705.设计哈希集合
	706.设计哈希映射
	707.设计链表
	1206.设计跳表
	1472.设计浏览器历史纪录
	1670.设计前中后队列

六、无法分类-8道
	链表与二叉树
		109.有序链表转换二叉树
		114. 二叉树展开为链表
		1367.二叉树中的列表
	链表与排序
		147.对链表进行插入排序(不会，排序)
		148.排序链表(不会，排序)
	

	382. 链表随机节点(蓄水池抽样算法？和384一起刷)
	2074.反转偶数长度组的节点(不会，暂时没找到好的解)

	2058.找出临界点之间的最小和最大距离(10min)
	



六、待完成-11道
	2.两数相加
	445.两数相加II

	86.分隔链表
	725.分隔链表
	
	138.复制带随机指针的链表

	430.扁平化多级双向链表
	432. 全 O(1) 的数据结构
	817.链表组件
	876.链表的中间结点
	1019.链表中的下一个更大节点
	1290.二进制链表转整数




剑指offer-18道
剑指 Offer 06. 从尾到头打印链表
剑指 Offer 18. 删除链表的节点
剑指 Offer 22. 链表中倒数第k个节点
剑指 Offer 24. 反转链表
剑指 Offer 35. 复杂链表的复制
剑指 Offer 52. 两个链表的第一个公共节点
剑指 Offer II 021. 删除链表的倒数第 n 个结点
剑指 Offer II 022. 链表中环的入口节点
剑指 Offer II 023. 两个链表的第一个重合节点	
剑指 Offer II 024. 反转链表
剑指 Offer II 025. 链表中的两数相加
剑指 Offer II 026. 重排链表
剑指 Offer II 027. 回文链表
剑指 Offer II 028. 展平多级双向链表	
剑指 Offer II 029. 排序的循环链表	
剑指 Offer II 031. 最近最少使用缓存
剑指 Offer II 077. 链表排序
剑指 Offer II 078. 合并排序链表

面试题-12道
面试题 02.01. 移除重复节点
面试题 02.02. 返回倒数第 k 个节点
面试题 02.03. 删除中间节点
面试题 02.04. 分割链表
面试题 02.05. 链表求和
面试题 02.06. 回文链表
面试题 02.07. 链表相交
面试题 02.08. 环路检测
面试题 03.03. 堆盘子
面试题 04.03. 特定深度节点链表
面试题 16.25. LRU 缓存
面试题 17.12. BiNode

ListNode dummy = new ListNode(-999);
dummy.next = head;
ListNode fast = dummy;
ListNode slow = dummy;
while(fast.next!=null&&fast.next.next!=null){
    fast = fast.next.next;
    slow = slow.next;
}
背下来，使用这种形式，无论奇偶数节点，slow的下一个节点都是中点
