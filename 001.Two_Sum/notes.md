Two Sum
=======
>使用散列表（哈希表）提高查找效率  
# 散列查找  
>时间复杂度几乎是常量：O(1)， 即查找时间与问题规模无关
# C++中哈希表的使用
```
#include<unordered_map>
unordered_map<int,int> hash;
hash[3] = 9;
hash.find(4)==hash.end();
//遍历hash table
for( mymap::iterator iter=mapping.begin();iter!=mapping.end();iter++ ){
    cout<<"key="<<iter->first<<" and value="<<iter->second<<endl;
}
...
```
# Java 中哈希表的使用
```
import java.util.HashMap;
import java.util.Map;
public class hashMap {
    public static void main(String[] args){
        Map<Integer> map = new HashMap<>();
        map.put(i);
    }
}
```
[HashMap](https://blog.csdn.net/wdays83892469/article/details/79615609)
## 目前已知的几种查找方法  

|查找方法|时间复杂读|
|----|----|
|顺序查找|O(n)|
|二分查找（静态查找）|O(log 2 n)|
|二叉搜索树|O(h) (h为树的高度)|
|平衡二叉树|O(log 2 n)|

## 散列查找法的两项基本工作
   * 计算位置：构造散列函数确定关键词存储位置； 
   * 解决冲突：应用某种策略解决多个关键词位置相同的问题 
## 哈希表
   * 类型名称:符号表(SymbolTable)  
   * 数据对象集：符号表是“名字(Name)-属性(Attribute)”对的集合  
   * 散列（Hashing）的基本思想是  
   1. 以关键字key为自变量，通过一个确定的函数 h（散列函数），计算出对应的函数值h(key)，作为数据对象的存储地址  
   2. 可能不同的关键字会映射到同一个散列地址上，即h(keyi) = h(keyj)（当keyi ≠keyj），称为“冲突(Collision)”    ----需要某种冲突解决策略 
## 散列函数的构造方法  
   考虑因素：  
   1. 计算简单，以便提高转换速度； 
   2. 关键词对应的地址空间分布均匀，以尽量减少冲突。  
   
### 数字关键词的散列函数构造  
   1. 直接定址法  
      h(key) = a * key + b  ；例：出生年份，h(key)=key-1990 
   2. 除留余数法（常用）  
      散列函数为：h(key) = key mod p
      这里：p = Tablesize ； 一般，p 取素数 
   3. 数字分析法  
      分析数字关键字在各位上的变化情况，取比较随机的位作为散列地址  
   4. 折叠法  
      把关键词分割成位数相同的几个部分，然后叠加  
   5. 平方取中法  
### 字符关键词的散列函数构造  
好的散列函数——移位法  
>把字符串视作32进制数  
   
h(“abcde”)=‘a’*324+’b’*323+’c’*322+’d’*32+’e’  
   
```
Index Hash ( const char *Key, int TableSize ) {        
unsigned int h = 0;     /* 散列函数值，初始化为0 */     
while ( *Key != ‘\0’)  /* 位移映射 */        
   h = ( h << 5 ) + *Key++;     
return  h % TableSize; 
```  
### 冲突处理方法
常用处理冲突的思路：   
* 换个位置：开放地址法  
* 同一位置的冲突对象组织在一起：链地址法  
#### 开放定址法(Open Addressing)  
* 若发生了第 i 次冲突，试探的下一个地址将增加di，基本公式是： hi(key) = (h(key)+di) mod TableSize     ( 1≤ i < TableSize )   
* di 决定了不同的解决冲突方案：线性探测、平方探测、双散列。
* 线性探测法（Linear Probing）  
  以增量序列 1，2，……，（TableSize -1） 循环试探下一个存储地址  
* 平方探测法（Quadratic Probing）--- 二次探测
  以增量序列12，-12，22，-22，……，q2，-q2 且q ≤ ⌊TableSize/2⌋ 循环试探下一个存储地址
  >有定理显示：如果散列表长度TableSize是某个4k+3（k是正整 数）形式的素数时，平方探测法就可以探查到整个散列表空间
* 双散列探测法(Double Hashing)  
   di 为i * h2(key)，h2(key)是另一个散列函数探测序列成：h2(key), 2h2(key), 3h2(key), ...
#### 散列表查找性能分析  
* 成功平均查找长度(ASLs) 
  查找表中关键词的平均查找比较次数(其冲突次数加1)  
  对表中每个元素插入的冲突次数加1进行平均  
* 不成功平均查找长度 (ASLu) 
   不在散列表中的关键词的平均查找次数(不成功)  
   一般方法：将不在散列表中的关键词分若干类，如：根据H(key)值分类 
#### 分离链接法  
将相应位置上冲突的所有关键词存储在同一个单链表中 
