package com.test.base;


//实现单例模式
public class Test02 {


}
/**
 * 饿汉法就是在第一次引用该类的时候就创建对象实例，而不管实际是否需要创建。代码如下：
 * public class Singleton {
 *     private static Singleton = new Singleton();
 *     private Singleton() {}
 *     public static getSingleton(){
 *         return singleton;
 *     }
 * }
 * 这样做的好处是编写简单，但是无法做到延迟创建对象。
 *
 */

/**
 *但是我们很多时候都希望对象可以尽可能地延迟加载，从而减小负载，所以就需要下面的懒汉法：
 *由私有构造器和一个公有静态工厂方法构成，在工厂方法中对singleton进行null判断，如果是null就new一个出来，
 * 最后返回singleton对象。这种方法可以实现延时加载，但是有一个致命弱点：线程不安全。如果有两条线程同时调
 * 用getSingleton()方法，就有很大可能导致重复创建对象。
 * public class Singleton {
 *     private static Singleton singleton = null;
 *     private Singleton(){}
 *     public static Singleton getSingleton() {
 *         if(singleton == null) singleton = new Singleton();
 *         return singleton;
 *     }
 * }
 */

/**
 * 这种写法考虑了线程安全，将对singleton的null判断以及new的部分使用synchronized进行加锁。同时，对singleton对象使
 * 用volatile关键字进行限制，保证其对所有线程的可见性，并且禁止对其进行指令重排序优化。如此即可从语义上保证这种单
 * 例模式写法是线程安全的。注意，这里说的是语义上，实际使用中还是存在小坑的，会在后文写到。
 * public class Singleton {
 *     private static volatile Singleton singleton = null;
 *     private Singleton(){}
 *     public static Singleton getSingleton(){
 *         synchronized (Singleton.class){
 *             if(singleton == null){
 *                 singleton = new Singleton();
 *             }
 *         }
 *         return singleton;
 *     }
 * }
 */

/**
 * 虽然上面这种写法是可以正确运行的，但是其效率低下，还是无法实际应用。因为每次调用getSingleton()方法，都必须在synchronized
 * 里进行排队，而真正遇到需要new的情况是非常少的。所以，就诞生了第三种写法：
 * 这种写法被称为“双重检查锁”，顾名思义，就是在getSingleton()方法中，进行两次null检查。看似多此一举，但实际上却极大提升了并发度，
 * 进而提升了性能。为什么可以提高并发度呢？就像上文说的，在单例中new的情况非常少，绝大多数都是可以并行的读操作。
 * 因此在加锁前多进行一次null检查就可以减少绝大多数的加锁操作，执行效率提高的目的也就达到了。
 * public class Singleton {
 *     private static volatile Singleton singleton = null;
 *     private Singleton(){}
 *     public static Singleton getSingleton(){
 *         if(singleton == null){
 *             synchronized (Singleton.class){
 *                 if(singleton == null){
 *                     singleton = new Singleton();
 *                 }
 *             }
 *         }
 *         return singleton;
 *     }
 * }
 */
