![logo][logo]


## 关于

组件化开发会涉及到模块与模块之间相互调用，而各模块之间又是解偶的，所以就产生了很多路由方案，但在 **[StaticBus][bus]** 看来，它们都略显复杂，**[StaticBus][bus]** 只需调用一个静态函数便可自由穿梭于各个模块，就像一辆巴士，由于是基于静态函数来实现，所以称她为 **[StaticBus][bus]**。


## 基本使用

在项目根目录的 `build.gradle` 中添加 `bus` 插件：

```groovy
buildscript {
    dependencies {
        ...
        classpath "gradle.plugin.com.blankj:bus-gradle-plugin:1.0"
    }
}
```

然后在 application 模块中使用该插件：

```groovy
apply plugin: "com.blankj.bus"
```

添加 bus 依赖：

```groovy
implementation "com.blankj:bus:1.0"
```

或者 [AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode) 依赖
```groovy
implementation "com.blankj:utilcode:1.21.0+"
```


比如 module0 中存在的 `Module0Activity.java`，我们通常都是在它内部写一个 `start` 函数来启动它，现在我们给它添加 `@BusUtils.Subscribe` 注解，并给注解的 `name` 赋唯一值，要注意，函数务必要 `public static` 哦：

```java
public class Module0Activity extends Activity {

    @BusUtils.Subscribe(name = "startModule0")
    public static boolean start(Context context, String name, int age) {
        Intent starter = new Intent(context, Module0Activity.class);
        starter.putExtra("name", name);
        starter.putExtra("age", age);
        context.startActivity(starter);
        return true;
    }
}
```

在其他模块通过 `BusUtils.post("startModule0", Context, String, int)` 即可访问到它，一定要注意 `name` 之后的参数顺序和个数一定要和前面声明的函数相一致，其返回值也就是前面函数的返回值：

```java
boolean result = BusUtils.post("startModule0", context, "blankj", 18);
```

点击编译之后会在项目根目录生成 `__bus__.json` 文件

```txt
{
  "startModule0": "boolean com.blankj.module0.Module0Activity.start(android.content.Context,java.lang.String,int)"
}
```


## 高级使用




[logo]: https://raw.githubusercontent.com/Blankj/StaticBus/master/art/logo.png
[bus]: https://github.com/Blankj/StaticBus