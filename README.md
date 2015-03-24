# MyAndroid
快速简单Android 开发框架
此框架包含 BaseActivity，BaseFragment，MyApplication, CustomAdapter，MyLog，BaseHttpActivity, BaseHttpFragment,CustomViewPagerAdapter等功能模块。此框架使用的网络请求是使用Google2013年I/O开发大会发布的Volley开源框架。

#使用
##1.BaseActivity：
Activity基础类，所有Activity都继承此基础Activity类，且必须实现BaseActivity的抽象方法。此类中实现了注解方式
查找UI,无需在使用繁琐的 findViewById 方式去查找控件。所有子类的activity都可以设置isHideActionBar的值是来控制是否显示ActionBar使用如下：

public class MainActivity extends BaseActivity {
    //此处就是使用注解查找id。
    @ViewInject(id = R.id.tv_title)
    TextView tv;


    @Override
    protected void initData() {
        //TODO 初始化一些数据
        tv.setText("测试框架集合");
    }

    @Override
    protected void loadData() {
        //TODO 加载一些数据，例如网络请求等。
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }
}


##2.BaseFragment：
所有的Fragment都继承BaseFragment，为了支持Fragment的注解查找Id，需要如下实现：
public class TestFragment extends BaseFragment {

    //注解查找id
    @ViewInject(id = R.id.tv_title)
    TextView tv;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.activity_main, container, false);
        BaseActivity.initInjectedView(this, viewRoot);
        return viewRoot;
    }

    @Override
    protected void getBundle() {

    }
    
    ##3.CustomAdapter：
    所有的数据适配器都继承此类，子类只需要实现父类CustomAdapter的抽象方法setHolderView（）即可。
    使用如下：
    public class TestAdapter extends CustomAdapter<String> {

    private Context context;

    public TestAdapter(Context context) {
        this.context = context;
    }


    @Override
    protected View setHolderView(int position, View convertView, ViewGroup parent) {

        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_main, null);
        }

        //此处也无需在使用findViewById 繁琐查找，直接使用ViewHolder类即可。
        TextView tv = ViewHolder.get(convertView, R.id.tv_title);

        return convertView;
    }
}
##4.MyLog：
日志打印控制类，便于整体控制release 和debug 模式下的调试。无需自己在release 时修改全局变量控制日志打印，代码
自动控制。

##5.MyApplication：
控制整个程序中的所有activity生命周期和activity退出的问题。具体看代码实现。

##6.BaseHttpActivity
所有有关网络请求的Activity都继承此类，子类Activity可以很方便的调用父类中的get和post网络请求方法，然后子类实现父类的抽象方法
和接口就可以拿到网络返回的数据了。

##7.BaseHttpFragment
所有有关网络请求的Fragment都继承此类，子类Fragment可以很方便的调用父类中的get和post网络请求方法。然后子类实现父类的抽象方法
和接口就可以拿到网络返回的数据了。

##8.CustomViewPagerAdapter 
所有的关于ViewPager使用的适配器都可以使用这个类，不需要重复写多个ViewPagerAdapter。




