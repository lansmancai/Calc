
import java.awt.*;
import static java.awt.BorderLayout.*;
import java.awt.event.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Date:15/12/2022
 * @author jinniu
 * @version 1.0
 */
public class Calc
{
	private Frame f = new Frame("计算器");
	private Panel p1 = new Panel();
	private Panel p2 = new Panel();
	private TextField tx = new TextField(30);
	private String calcstr = "";
	
	//根据表达式进行计算并返回结果字符串
	public String calccount(String str)
	{
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine SE = manager.getEngineByName("js");
		String ans = "";
        try 
		{
            ans = SE.eval(str).toString();
        }
        catch (ScriptException e) 
		{
            e.printStackTrace();
        }
		return ans;
	}
		
	public void init()
	{
		//计算器上文本框回车后响应函数
		class TtEnter implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String calcresult = calccount(calcstr);
				tx.setText(calcresult);
				calcstr = "";
			}
		}
		tx.addActionListener(new TtEnter());
		
		p1.add(tx);
		f.add(p1, NORTH);
		p2.setLayout(new GridLayout(3, 5, 4, 4));
		String[] name = {"0", "1", "2", "3","4", "5", "6", "7", "8", "9","+", "-", "*", "/", "."};
		
		//计算器上各个按钮的点击响应函数
		class ButtonClick implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				calcstr = calcstr + e.getActionCommand();
				tx.setText(calcstr);
			}
		}
		//根据name数组部署上各个按钮
		for (var i = 0; i < name.length; i++)
		{
			var button = new Button(name[i]);
			button.addActionListener(new ButtonClick());
			p2.add(button);
		}
		f.add(p2);
		//窗口事件响应函数
		class MyListener extends WindowAdapter
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		}
		f.addWindowListener(new MyListener());
		f.setBounds(300, 300, 2500, 2000);
		f.pack();
		f.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new Calc().init();
	}
}

