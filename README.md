> 打造万能的适配器，超级简单使用，省时省力。

> Kotlin,Androidx

> Step 1. Add the JitPack repository to your build file
> 
> Add it in your root build.gradle at the end of repositories:

	
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

> Step 2. Add the dependency

	
```
dependencies {
    implementation 'com.github.fingerth:commonadapterkt:1.0.0'
}
```


### CommonAdapter<T> 一个简单的ListView和GrideView的适配器

```
    //val lv:ListView
    //val list: ArrayList<String>
    
    lv.adapter = object : CommonAdapter<String>(this, list,android.R.layout.simple_list_item_1) {
        override fun convert(holder: Holder, position: Int, data: String) {
            holder.setText(android.R.id.text1, data)
        }
    }
```

### CommonPagerAdapter<T> 一款简单的ViewPager适配器


```
     //val vp:ViewPager 
     //val list: ArrayList<String>
     
     vp.adapter = object : CommonPagerAdapter<String>(this, list, R.layout.pager_item_view) {
        override fun convert(holder: Holder, position: Int, data: String) {
            holder.setText(R.id.tv, data)
            holder.setText(R.id.tv2, position.toString())
        }
    }

```
    
    > 要支持无限滑怎办，不要担心，用这个UnlimitedSlidePagerAdapter<T>
    
    
``` 
    //val vp:ViewPager 
    //val list:ArrayList<String>
    
    vp.adapter = object : UnlimitedSlidePagerAdapter<String>(this, list, R.layout.pager_item_view) {
        override fun convert(holder: Holder, position: Int, data: String) {
            holder.setText(R.id.tv, data)
            holder.setText(R.id.tv2, position.toString())
        }
    }
    vp.setCurrentItem(ad.getMidPosition(),false)
       
```
### CommonRecyclerAdapter<T> 一款简单的RecyclerView适配器

> 正常使用

```

    //val rv:RecyclerView 
    //val list:ArrayList<String>
    
    rv.layoutManager = GridLayoutManager(this, 2)
    rv.adapter = object : CommonRecyclerAdapter<String>(this, list) {
        override fun setLayoutId(viewType: Int): Int {
            return android.R.layout.simple_list_item_1
        }

        override fun onBind(holder: Holder, RealPosition: Int, data: String) {
            holder.setText(android.R.id.text1, data)
        }
    }
```
> 有头部，有尾部


```
    //val rv:RecyclerView 
    //val list:ArrayList<String>

    val adapter = object : CommonRecyclerAdapter<String>(this, list) {
        override fun setLayoutId(viewType: Int): Int {
            return android.R.layout.simple_list_item_1
        }

        override fun onBind(holder: Holder, RealPosition: Int, data: String) {
            holder.setText(android.R.id.text1, data)
        }
    }
    val mHeaderView = View.inflate(this, R.layout.view_header, null)
    adapter.setHeaderView(mHeaderView)
    val mFootView = View.inflate(this, R.layout.view_foot, null)
    adapter.setFootView(mFootView)
    
    rv.layoutManager = GridLayoutManager(this, 2)
    rv.adapter = adapter
    
```

> 不同Item的使用


```
    //val rv:RecyclerView 
    //val list:ArrayList<String>
    
    val adapter = object : CommonRecyclerAdapter<String>(this, list) {
        override fun itemViewType(position: Int): Int {
            return if (position % 3 == 1) {
                0x88
            } else super.itemViewType(position)
        }
        override fun setLayoutId(viewType: Int): Int {
            return when (viewType) {
                0x88 -> R.layout.view_foot
                else -> android.R.layout.simple_list_item_1
            }
        }

        override fun onBind(holder: Holder, RealPosition: Int, data: String) {
            when (holder.itemViewType) {
                0x88 -> holder.setText(R.id.tv, data)
                else -> holder.setText(android.R.id.text1, data)
            }
        }
    }
    rv.layoutManager = GridLayoutManager(this, 2)
    //rv.layoutManager = LinearLayoutManager(this)
    rv.adapter = adapter
```











