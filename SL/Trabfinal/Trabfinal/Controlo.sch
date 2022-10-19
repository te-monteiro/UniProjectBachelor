<?xml version="1.0" encoding="UTF-8"?>
<drawing version="7">
    <attr value="spartan3e" name="DeviceFamilyName">
        <trait delete="all:0" />
        <trait editname="all:0" />
        <trait edittrait="all:0" />
    </attr>
    <netlist>
        <signal name="CLK" />
        <signal name="SEL" />
        <signal name="PASSTHROUGH" />
        <signal name="DEC" />
        <signal name="ADD" />
        <signal name="ANL" />
        <signal name="LOADTEMP" />
        <signal name="CLEARTEMP" />
        <signal name="RESET" />
        <signal name="GO" />
        <signal name="OK" />
        <port polarity="Input" name="CLK" />
        <port polarity="Output" name="SEL" />
        <port polarity="Output" name="PASSTHROUGH" />
        <port polarity="Output" name="DEC" />
        <port polarity="Output" name="ADD" />
        <port polarity="Output" name="ANL" />
        <port polarity="Output" name="LOADTEMP" />
        <port polarity="Output" name="CLEARTEMP" />
        <port polarity="Input" name="RESET" />
        <port polarity="Input" name="GO" />
        <port polarity="Output" name="OK" />
    </netlist>
    <sheet sheetnum="1" width="3520" height="2720">
        <branch name="CLK">
            <wire x2="608" y1="2560" y2="2560" x1="272" />
        </branch>
        <iomarker fontsize="28" x="272" y="2560" name="CLK" orien="R180" />
        <branch name="SEL">
            <wire x2="3152" y1="368" y2="368" x1="2912" />
        </branch>
        <branch name="PASSTHROUGH">
            <wire x2="3152" y1="656" y2="656" x1="2912" />
        </branch>
        <branch name="DEC">
            <wire x2="3152" y1="944" y2="944" x1="2912" />
        </branch>
        <branch name="ADD">
            <wire x2="3152" y1="1232" y2="1232" x1="2912" />
        </branch>
        <branch name="ANL">
            <wire x2="3152" y1="1520" y2="1520" x1="2912" />
        </branch>
        <branch name="LOADTEMP">
            <wire x2="3152" y1="1808" y2="1808" x1="2912" />
        </branch>
        <branch name="CLEARTEMP">
            <wire x2="3152" y1="2096" y2="2096" x1="2912" />
        </branch>
        <iomarker fontsize="28" x="3152" y="368" name="SEL" orien="R0" />
        <iomarker fontsize="28" x="3152" y="656" name="PASSTHROUGH" orien="R0" />
        <iomarker fontsize="28" x="3152" y="944" name="DEC" orien="R0" />
        <iomarker fontsize="28" x="3152" y="1232" name="ADD" orien="R0" />
        <iomarker fontsize="28" x="3152" y="1520" name="ANL" orien="R0" />
        <iomarker fontsize="28" x="3152" y="1808" name="LOADTEMP" orien="R0" />
        <iomarker fontsize="28" x="3152" y="2096" name="CLEARTEMP" orien="R0" />
        <branch name="RESET">
            <wire x2="592" y1="368" y2="368" x1="304" />
        </branch>
        <branch name="GO">
            <wire x2="592" y1="560" y2="560" x1="256" />
        </branch>
        <iomarker fontsize="28" x="304" y="368" name="RESET" orien="R180" />
        <iomarker fontsize="28" x="256" y="560" name="GO" orien="R180" />
        <iomarker fontsize="28" x="3264" y="2480" name="OK" orien="R0" />
        <branch name="OK">
            <wire x2="3264" y1="2480" y2="2480" x1="2912" />
        </branch>
    </sheet>
</drawing>