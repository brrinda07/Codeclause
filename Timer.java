/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.timer;
/**
 *
 * @author Brrin
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timer extends JFrame implements Runnable, ActionListener {
	JPanel p;
	JLabel display;
	JButton start, stop, reset;
	int hour, minute, second, millisecond;
	String disp;
	boolean on;
	Timer()
	{
		on = false;
		p = new JPanel();
		setLayout(null);
                setTitle("TIMER & STOPWATCH");
                JLabel text = new JLabel("TIMER - STOPWATCH");
                text.setFont(new Font("Times New Roman",Font.BOLD,38));
                text.setBounds(765,150, 500,40);
                add(text);
                
		hour = minute = second = millisecond = 0;
		display = new JLabel();
		disp = "00 : 00 : 00 : 000";
		display.setText(disp);
                display.setFont(new Font("Times New Roman",Font.BOLD,32));
		display.setBounds(830,300,500,90);
                
                add(display);
                
		start = new JButton("START");
		start.addActionListener((ActionListener) this);
                start.setBounds(830,450,250,90);
                start.setFont(new Font("Times New Roman",Font.BOLD,32));
		add(start);

		reset = new JButton("RESET");
		reset.addActionListener((ActionListener) this);
                reset.setBounds(830,550,250,90);
		reset.setFont(new Font("Times New Roman",Font.BOLD,32));
                add(reset);

		stop = new JButton("STOP");
		stop.addActionListener((ActionListener) this);
                stop.setBounds(830,650,250,90);
		stop.setFont(new Font("Times New Roman",Font.BOLD,32));
                add(stop);

		add(p);
                setVisible(true);
                setSize(500,500);

		new Thread(this, "StopWatch").start();
	}

	public void reset()
	{
		try {
			Thread.sleep(1);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		hour = minute = second = millisecond = 0;
	}

	public void update()
	{
		millisecond++;
		if (millisecond == 1000) {
			millisecond = 0;
			second++;
			if (second == 60) {
				second = 0;
				minute++;
				if (minute == 60) {
					minute = 0;
					hour++;
				}
			}
		}
	}

	public void changeLabel()
	{
		if (hour < 10)
			disp = "0" + hour + " : ";
		else
			disp = hour + " : ";

		if (minute < 10)
			disp += "0" + minute + " : ";
		else
			disp += minute + " : ";

		if (second < 10)
			disp += "0" + second + " : ";
		else
			disp += second + " : ";

		if (millisecond < 10)
			disp += "00" + millisecond;
		else if (millisecond < 100)
			disp += "0" + millisecond;
		else
			disp += millisecond;

		display.setText(disp);
	}
	public void run()
	{
	while (on) {
        	try {
        		Thread.sleep(1);
			update();
			changeLabel();
                    }
		catch (InterruptedException e) {
			System.out.println(e);
			}
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == start) {
			on = true;
			new Thread(this, "StopWatch").start();
		}
		if (e.getSource() == reset) {
			on = false;
			reset();
			changeLabel();
		}
		if (e.getSource() == stop) {
			on = false;
		}
	}
	public static void main(String args[]){
		Timer s=new Timer();
		s.reset();
		s.update();
		s.changeLabel();
		s.run();
	}
}

