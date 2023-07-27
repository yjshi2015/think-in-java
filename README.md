# think-in-java
Think-In-Java 重温经典

## Object-Oriented 
面向对象是一种编程范式，它将程序设计看作一组对象的集合，每个对象有自己的状态（属性）和行为（方法）。OOP的核心是**将现实世界中的事物抽象成程序中的对象，并通过对象之间的交互实现程序的功能**。

面向对象编程的主要特点包括：
封装（Encapsulation）：将数据和方法封装在一个对象中，通过定义公共接口来控制对内部数据的访问。
继承（Inheritance）：通过继承机制，一个类可以从另一个类派生出来，继承父类的属性和方法，并可以添加自己的特定功能。
多态（Polymorphism）：同一个方法可以在不同的对象类型上具有不同的行为，提高代码的灵活性和可扩展性。

面向对象编程的优点包括：
代码可重用性：通过封装和继承，可以更好地组织和重用代码。
可维护性：面向对象编程使得代码更易于理解、修改和扩展。
模块化：通过将功能划分为对象，可以更好地组织和管理代码。
抽象性：面向对象编程允许将复杂的现实世界问题抽象成简单的对象和关系，提高了代码的可读性和可理解性。

## Queue
- Queue可以从两个维度来分类。一个维度是**阻塞与非阻塞**,所谓阻塞是指队列已满时入队操作阻塞,队列为空时出队操作阻塞.
另一个维度是**单端与双端**,单端指的是只能队尾入队,队首出队;而双端指的是队首队尾
皆可入队出队.Java并发包里**阻塞队列都用Blocking关键字标示,单队列使用Queue标识,双队列使用DeQue标示**

- 实际工作中,一般不建议使用无界队列(即队列的大小无限制),因为数据量大了很容易造成OOM,只有ArrayBlockingQueue和LinkedBlockingQueue是支持有界的.

## concurrent
- 该包下的工具类是线程安全的,包括list/map/Queue/autoInteger等等
- 对于简单的并行任务,可以通过"线程池+Future"的方案来解决
- 对于任务之间有聚合关系,不管是AND聚合,还是OR聚合,都可以通过"CompletableFuture"来解决
- 对于批量的并行任务,则可以通过"CompletionService"来解决


## 度量性能的指标
- 延迟:指发出请求到接收到响应这个过程的时间,常用的指标有TP99 / TP999
- 吞吐量:指单位时间内能处理请求的数量,常用指标 QPS/TPS

## 最佳线程数
- CPU密集型: 最佳线程数 = CPU核数 + 1
  理论上线程数量=CPU核数,但在工程中,当线程因为偶尔的内存页失效或其他原因导致阻塞时,这个额外的线程可以顶上,从而保证
  CPU的利用率达到100%
  
- IO密集型:  最佳线程数 = CPU核数 * (1 + IO耗时/CPU耗时)
  令R=IO耗时/CPU耗时,则意味着当线程A执行完IO操作后,另外R个线程正好执行完各自的CPU计算.这样CPU的利用率就达到了100%,
  同时IO设备的使用率也达到了100%
  
- 针对CPU密集型/IO密集场景下"最佳线程数"的计算原理,开抽象为:让所有的任务由并驾齐驱改为错峰执行,以便提高硬件设备的利
  用率.
  
## Java线程状态

```mermaid
flowchat
st=>start: 初始状态NEW
run=>condition: RUNNABLE可运行/运行状态
sleep=>operation: 休眠状态BLOCKED/ WATING/ TIME_WAITING
e=>end: 终止状态TERMINATED

st->run
run(yes)->e
run(no)->sleep
```

- 1.RUNNABLE与BLOCKED的状态转换
 + 只有一种场景会触发这种转换,就是线程等待synchronized的隐式锁.synchronized修饰的方法/代码块同一时刻只允许一个
   线程执行,其他线程只能等待,这种情况下,等待的线程就会从RUNNABLE转换到BLOCKED状态.而当等待的线程获得synchronized
   这个隐式锁,就又会从BLOCKED状态转换为RUNNABLE状态
- 2.RUNNABLE与WAITING的状态转换
 + 活动synchronized隐式锁的线程,调用无参数的Object.wait()方法.
 + 调用无参数的Thread.join()方法.其中join是一种线程同步方法.例如有一个线程对象A,当调用A.join()的时候,执行这条语句
   的线程会等待thread A执行完,而等待中的这个线程,其状态就会从RUNNABLE转换为WAITING,当Thread A执行完,原来等待它的
   线程就又会从WAITING转换为RUNNABLE
 + 调用LockSupport.park()
- 3.RUNNABLE与TIMED_WAITTING的状态转换
 + 调用*带超时参数*的Thread.sleep(long millis)方法
 + 获得synchronized隐式锁的线程,调用*带超时参数*的Object.wait(long timeout)方法
 + 调用带*超时参数*的Thread.join(long millis)方法
 + 调用*带超时参数*的LockSupport.parkNanos(Object blocker,long deadline)方法
 + 调用*带超时参数*的LockSupport.partUntil(long deadline)方法
- 4.RUNNABLE到TERMINATED状态的转换
 + 线程执行完run方法后,会自动转换到TERMINATED状态.
 + 执行run方法的时候抛出异常,也会导致线程终止
 *如果我们需要强制中断run方法的执行,例如run方法访问一个很慢的网络,我们等不下去了,想要终止怎么办?*
 ++ Java的Thread类里有个一个stop方法,不过已经不建议使用了.
    stop方法会真的杀死线程,不给线程喘息的机会.如果线程持有ReentrantLock锁,被stop的线程并不会自动调用ReentrantLock
    的unlock方法去释放锁,那么其他线程就再也没有机会去获得ReentrantLock锁了.所以stop方法不再建议使用.同理的还有suspend/
    resume方法
 ++ 使用interrupt()方法通知.被通知的线程可以通过主动检测isInterrupted,或者异常InterruptedException来收到通知.
    interrupt方法仅仅是通知线程,线程有机会执行一些后续操作,同时也可以无视这个通知.
    *注意:通过InterruptedException方式通知线程,中断标识会被清除,即此时判断isInterrupted时为false,故在通过
    InterruptException异常通知时,需要重置中断标识,即Thread A.interrupt()*
    
    +++ 当线程A处于WAITTING/TIMED_WAITTING状态时,如果其他线程调用线程A的interrupt方法,会使线程A返回到RUNNABLE状态,
        同时线程A的代码会触发InterruptException异常.上面提到转换到WAITTING/TIMED_WAITTING状态的触发条件,都是调用
        了类似wait()/join()/sleep()这样的方法,这些方法的签名,发现都会有throws InterruptedExcepiton这个异常.这个异常
        触发的条件就是:其他线程调用了该线程的interrupt()方法
