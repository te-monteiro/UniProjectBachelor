<?xml version="1.0" encoding="UTF-8"?>
<drawing version="7">
    <attr value="spartan3e" name="DeviceFamilyName">
        <trait delete="all:0" />
        <trait editname="all:0" />
        <trait edittrait="all:0" />
    </attr>
    <netlist>
        <signal name="CLEAR" />
        <signal name="LOAD" />
        <signal name="CLK" />
        <signal name="D(7)" />
        <signal name="D(6)" />
        <signal name="D(5)" />
        <signal name="D(4)" />
        <signal name="D(3)" />
        <signal name="D(2)" />
        <signal name="D(1)" />
        <signal name="D(0)" />
        <signal name="Q(4)" />
        <signal name="Q(5)" />
        <signal name="Q(6)" />
        <signal name="Q(7)" />
        <signal name="Q(3)" />
        <signal name="Q(2)" />
        <signal name="Q(1)" />
        <signal name="Q(0)" />
        <signal name="D(7:0)" />
        <signal name="Q(7:0)" />
        <port polarity="Input" name="CLEAR" />
        <port polarity="Input" name="LOAD" />
        <port polarity="Input" name="CLK" />
        <port polarity="Input" name="D(7:0)" />
        <port polarity="Output" name="Q(7:0)" />
        <blockdef name="REGi">
            <timestamp>2016-11-19T23:48:26</timestamp>
            <rect width="224" x="64" y="-288" height="224" />
            <line x2="176" y1="-288" y2="-352" x1="176" />
            <line x2="224" y1="-64" y2="0" x1="224" />
            <line x2="128" y1="-64" y2="0" x1="128" />
            <line x2="0" y1="-128" y2="-128" x1="64" />
            <line x2="0" y1="-224" y2="-224" x1="64" />
        </blockdef>
        <block symbolname="REGi" name="XLXI_7">
            <blockpin signalname="D(4)" name="Di" />
            <blockpin signalname="Q(4)" name="Qi" />
            <blockpin signalname="CLK" name="CLK" />
            <blockpin signalname="LOAD" name="LOAD" />
            <blockpin signalname="CLEAR" name="CLEAR" />
        </block>
        <block symbolname="REGi" name="XLXI_9">
            <blockpin signalname="D(3)" name="Di" />
            <blockpin signalname="Q(3)" name="Qi" />
            <blockpin signalname="CLK" name="CLK" />
            <blockpin signalname="LOAD" name="LOAD" />
            <blockpin signalname="CLEAR" name="CLEAR" />
        </block>
        <block symbolname="REGi" name="XLXI_8">
            <blockpin signalname="D(2)" name="Di" />
            <blockpin signalname="Q(2)" name="Qi" />
            <blockpin signalname="CLK" name="CLK" />
            <blockpin signalname="LOAD" name="LOAD" />
            <blockpin signalname="CLEAR" name="CLEAR" />
        </block>
        <block symbolname="REGi" name="XLXI_3">
            <blockpin signalname="D(5)" name="Di" />
            <blockpin signalname="Q(5)" name="Qi" />
            <blockpin signalname="CLK" name="CLK" />
            <blockpin signalname="LOAD" name="LOAD" />
            <blockpin signalname="CLEAR" name="CLEAR" />
        </block>
        <block symbolname="REGi" name="XLXI_2">
            <blockpin signalname="D(6)" name="Di" />
            <blockpin signalname="Q(6)" name="Qi" />
            <blockpin signalname="CLK" name="CLK" />
            <blockpin signalname="LOAD" name="LOAD" />
            <blockpin signalname="CLEAR" name="CLEAR" />
        </block>
        <block symbolname="REGi" name="XLXI_1">
            <blockpin signalname="D(7)" name="Di" />
            <blockpin signalname="Q(7)" name="Qi" />
            <blockpin signalname="CLK" name="CLK" />
            <blockpin signalname="LOAD" name="LOAD" />
            <blockpin signalname="CLEAR" name="CLEAR" />
        </block>
        <block symbolname="REGi" name="XLXI_10">
            <blockpin signalname="D(1)" name="Di" />
            <blockpin signalname="Q(1)" name="Qi" />
            <blockpin signalname="CLK" name="CLK" />
            <blockpin signalname="LOAD" name="LOAD" />
            <blockpin signalname="CLEAR" name="CLEAR" />
        </block>
        <block symbolname="REGi" name="XLXI_11">
            <blockpin signalname="D(0)" name="Di" />
            <blockpin signalname="Q(0)" name="Qi" />
            <blockpin signalname="CLK" name="CLK" />
            <blockpin signalname="LOAD" name="LOAD" />
            <blockpin signalname="CLEAR" name="CLEAR" />
        </block>
    </netlist>
    <sheet sheetnum="1" width="5440" height="3520">
        <instance x="2336" y="1824" name="XLXI_7" orien="R0">
        </instance>
        <instance x="2864" y="1824" name="XLXI_9" orien="R0">
        </instance>
        <instance x="3408" y="1824" name="XLXI_8" orien="R0">
        </instance>
        <instance x="1792" y="1808" name="XLXI_3" orien="R0">
        </instance>
        <instance x="1232" y="1808" name="XLXI_2" orien="R0">
        </instance>
        <instance x="3936" y="1824" name="XLXI_10" orien="R0">
        </instance>
        <instance x="4480" y="1840" name="XLXI_11" orien="R0">
        </instance>
        <instance x="688" y="1808" name="XLXI_1" orien="R0">
        </instance>
        <branch name="CLEAR">
            <wire x2="576" y1="1584" y2="1584" x1="400" />
            <wire x2="672" y1="1584" y2="1584" x1="576" />
            <wire x2="688" y1="1584" y2="1584" x1="672" />
            <wire x2="576" y1="1376" y2="1584" x1="576" />
            <wire x2="1040" y1="1376" y2="1376" x1="576" />
            <wire x2="1040" y1="1376" y2="1584" x1="1040" />
            <wire x2="1232" y1="1584" y2="1584" x1="1040" />
            <wire x2="1584" y1="1376" y2="1376" x1="1040" />
            <wire x2="1584" y1="1376" y2="1584" x1="1584" />
            <wire x2="1792" y1="1584" y2="1584" x1="1584" />
            <wire x2="2144" y1="1376" y2="1376" x1="1584" />
            <wire x2="2144" y1="1376" y2="1600" x1="2144" />
            <wire x2="2336" y1="1600" y2="1600" x1="2144" />
            <wire x2="2688" y1="1376" y2="1376" x1="2144" />
            <wire x2="2688" y1="1376" y2="1600" x1="2688" />
            <wire x2="2864" y1="1600" y2="1600" x1="2688" />
            <wire x2="3216" y1="1376" y2="1376" x1="2688" />
            <wire x2="3216" y1="1376" y2="1600" x1="3216" />
            <wire x2="3408" y1="1600" y2="1600" x1="3216" />
            <wire x2="3760" y1="1376" y2="1376" x1="3216" />
            <wire x2="3760" y1="1376" y2="1600" x1="3760" />
            <wire x2="3936" y1="1600" y2="1600" x1="3760" />
            <wire x2="4288" y1="1376" y2="1376" x1="3760" />
            <wire x2="4288" y1="1376" y2="1616" x1="4288" />
            <wire x2="4480" y1="1616" y2="1616" x1="4288" />
        </branch>
        <branch name="LOAD">
            <wire x2="576" y1="1680" y2="1680" x1="384" />
            <wire x2="672" y1="1680" y2="1680" x1="576" />
            <wire x2="688" y1="1680" y2="1680" x1="672" />
            <wire x2="576" y1="1680" y2="1872" x1="576" />
            <wire x2="1040" y1="1872" y2="1872" x1="576" />
            <wire x2="1584" y1="1872" y2="1872" x1="1040" />
            <wire x2="2144" y1="1872" y2="1872" x1="1584" />
            <wire x2="2688" y1="1872" y2="1872" x1="2144" />
            <wire x2="3216" y1="1872" y2="1872" x1="2688" />
            <wire x2="3760" y1="1872" y2="1872" x1="3216" />
            <wire x2="4288" y1="1872" y2="1872" x1="3760" />
            <wire x2="1040" y1="1680" y2="1872" x1="1040" />
            <wire x2="1232" y1="1680" y2="1680" x1="1040" />
            <wire x2="1584" y1="1680" y2="1872" x1="1584" />
            <wire x2="1792" y1="1680" y2="1680" x1="1584" />
            <wire x2="2144" y1="1696" y2="1872" x1="2144" />
            <wire x2="2336" y1="1696" y2="1696" x1="2144" />
            <wire x2="2688" y1="1696" y2="1872" x1="2688" />
            <wire x2="2864" y1="1696" y2="1696" x1="2688" />
            <wire x2="3216" y1="1696" y2="1872" x1="3216" />
            <wire x2="3408" y1="1696" y2="1696" x1="3216" />
            <wire x2="3760" y1="1696" y2="1872" x1="3760" />
            <wire x2="3936" y1="1696" y2="1696" x1="3760" />
            <wire x2="4288" y1="1712" y2="1872" x1="4288" />
            <wire x2="4480" y1="1712" y2="1712" x1="4288" />
        </branch>
        <iomarker fontsize="28" x="352" y="2032" name="CLK" orien="R180" />
        <branch name="CLK">
            <wire x2="816" y1="2032" y2="2032" x1="352" />
            <wire x2="1360" y1="2032" y2="2032" x1="816" />
            <wire x2="1920" y1="2032" y2="2032" x1="1360" />
            <wire x2="2464" y1="2032" y2="2032" x1="1920" />
            <wire x2="2992" y1="2032" y2="2032" x1="2464" />
            <wire x2="3536" y1="2032" y2="2032" x1="2992" />
            <wire x2="4064" y1="2032" y2="2032" x1="3536" />
            <wire x2="4608" y1="2032" y2="2032" x1="4064" />
            <wire x2="816" y1="1808" y2="1824" x1="816" />
            <wire x2="816" y1="1824" y2="2032" x1="816" />
            <wire x2="1360" y1="1808" y2="2032" x1="1360" />
            <wire x2="1920" y1="1808" y2="2032" x1="1920" />
            <wire x2="2464" y1="1824" y2="2032" x1="2464" />
            <wire x2="2992" y1="1824" y2="2032" x1="2992" />
            <wire x2="3536" y1="1824" y2="2032" x1="3536" />
            <wire x2="4064" y1="1824" y2="2032" x1="4064" />
            <wire x2="4608" y1="1840" y2="2032" x1="4608" />
        </branch>
        <branch name="D(7)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="864" y="1248" type="branch" />
            <wire x2="864" y1="1056" y2="1248" x1="864" />
            <wire x2="864" y1="1248" y2="1440" x1="864" />
            <wire x2="864" y1="1440" y2="1456" x1="864" />
        </branch>
        <branch name="D(6)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1408" y="1248" type="branch" />
            <wire x2="1408" y1="1056" y2="1248" x1="1408" />
            <wire x2="1408" y1="1248" y2="1440" x1="1408" />
            <wire x2="1408" y1="1440" y2="1456" x1="1408" />
        </branch>
        <branch name="D(5)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1968" y="1248" type="branch" />
            <wire x2="1968" y1="1056" y2="1248" x1="1968" />
            <wire x2="1968" y1="1248" y2="1440" x1="1968" />
            <wire x2="1968" y1="1440" y2="1456" x1="1968" />
        </branch>
        <branch name="D(4)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="2512" y="1248" type="branch" />
            <wire x2="2512" y1="1056" y2="1248" x1="2512" />
            <wire x2="2512" y1="1248" y2="1456" x1="2512" />
            <wire x2="2512" y1="1456" y2="1472" x1="2512" />
        </branch>
        <branch name="D(3)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="3040" y="1248" type="branch" />
            <wire x2="3040" y1="1056" y2="1248" x1="3040" />
            <wire x2="3040" y1="1248" y2="1456" x1="3040" />
            <wire x2="3040" y1="1456" y2="1472" x1="3040" />
        </branch>
        <branch name="D(2)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="3584" y="1248" type="branch" />
            <wire x2="3584" y1="1056" y2="1248" x1="3584" />
            <wire x2="3584" y1="1248" y2="1456" x1="3584" />
            <wire x2="3584" y1="1456" y2="1472" x1="3584" />
        </branch>
        <branch name="D(1)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="4112" y="1248" type="branch" />
            <wire x2="4112" y1="1056" y2="1248" x1="4112" />
            <wire x2="4112" y1="1248" y2="1456" x1="4112" />
            <wire x2="4112" y1="1456" y2="1472" x1="4112" />
        </branch>
        <branch name="D(0)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="4656" y="1248" type="branch" />
            <wire x2="4656" y1="1056" y2="1248" x1="4656" />
            <wire x2="4656" y1="1248" y2="1472" x1="4656" />
            <wire x2="4656" y1="1472" y2="1488" x1="4656" />
        </branch>
        <branch name="Q(4)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="2560" y="2240" type="branch" />
            <wire x2="2560" y1="1824" y2="1840" x1="2560" />
            <wire x2="2560" y1="1840" y2="2240" x1="2560" />
            <wire x2="2560" y1="2240" y2="2368" x1="2560" />
        </branch>
        <branch name="Q(5)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="2016" y="2240" type="branch" />
            <wire x2="2016" y1="1808" y2="1824" x1="2016" />
            <wire x2="2016" y1="1824" y2="2240" x1="2016" />
            <wire x2="2016" y1="2240" y2="2368" x1="2016" />
        </branch>
        <branch name="Q(6)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1456" y="2240" type="branch" />
            <wire x2="1456" y1="1808" y2="1824" x1="1456" />
            <wire x2="1456" y1="1824" y2="2240" x1="1456" />
            <wire x2="1456" y1="2240" y2="2368" x1="1456" />
        </branch>
        <branch name="Q(7)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="912" y="2240" type="branch" />
            <wire x2="912" y1="1808" y2="1824" x1="912" />
            <wire x2="912" y1="1824" y2="2240" x1="912" />
            <wire x2="912" y1="2240" y2="2368" x1="912" />
        </branch>
        <branch name="Q(3)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="3088" y="2240" type="branch" />
            <wire x2="3088" y1="1824" y2="1840" x1="3088" />
            <wire x2="3088" y1="1840" y2="2240" x1="3088" />
            <wire x2="3088" y1="2240" y2="2368" x1="3088" />
        </branch>
        <branch name="Q(2)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="3632" y="2240" type="branch" />
            <wire x2="3632" y1="1824" y2="1840" x1="3632" />
            <wire x2="3632" y1="1840" y2="2240" x1="3632" />
            <wire x2="3632" y1="2240" y2="2368" x1="3632" />
        </branch>
        <branch name="Q(1)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="4160" y="2240" type="branch" />
            <wire x2="4160" y1="1824" y2="1840" x1="4160" />
            <wire x2="4160" y1="1840" y2="2240" x1="4160" />
            <wire x2="4160" y1="2240" y2="2368" x1="4160" />
        </branch>
        <branch name="Q(0)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="4704" y="2240" type="branch" />
            <wire x2="4704" y1="1840" y2="1856" x1="4704" />
            <wire x2="4704" y1="1856" y2="2240" x1="4704" />
            <wire x2="4704" y1="2240" y2="2368" x1="4704" />
        </branch>
        <branch name="D(7:0)">
            <wire x2="864" y1="960" y2="960" x1="368" />
            <wire x2="1408" y1="960" y2="960" x1="864" />
            <wire x2="1968" y1="960" y2="960" x1="1408" />
            <wire x2="2512" y1="960" y2="960" x1="1968" />
            <wire x2="3040" y1="960" y2="960" x1="2512" />
            <wire x2="3568" y1="960" y2="960" x1="3040" />
            <wire x2="3584" y1="960" y2="960" x1="3568" />
            <wire x2="4096" y1="960" y2="960" x1="3584" />
            <wire x2="4112" y1="960" y2="960" x1="4096" />
            <wire x2="4640" y1="960" y2="960" x1="4112" />
            <wire x2="4656" y1="960" y2="960" x1="4640" />
            <wire x2="5232" y1="960" y2="960" x1="4656" />
        </branch>
        <bustap x2="864" y1="960" y2="1056" x1="864" />
        <bustap x2="1408" y1="960" y2="1056" x1="1408" />
        <bustap x2="1968" y1="960" y2="1056" x1="1968" />
        <bustap x2="2512" y1="960" y2="1056" x1="2512" />
        <bustap x2="3040" y1="960" y2="1056" x1="3040" />
        <bustap x2="3584" y1="960" y2="1056" x1="3584" />
        <bustap x2="4656" y1="960" y2="1056" x1="4656" />
        <bustap x2="4112" y1="960" y2="1056" x1="4112" />
        <iomarker fontsize="28" x="368" y="960" name="D(7:0)" orien="R180" />
        <branch name="Q(7:0)">
            <wire x2="912" y1="2464" y2="2464" x1="272" />
            <wire x2="1456" y1="2464" y2="2464" x1="912" />
            <wire x2="2016" y1="2464" y2="2464" x1="1456" />
            <wire x2="2560" y1="2464" y2="2464" x1="2016" />
            <wire x2="3072" y1="2464" y2="2464" x1="2560" />
            <wire x2="3088" y1="2464" y2="2464" x1="3072" />
            <wire x2="3632" y1="2464" y2="2464" x1="3088" />
            <wire x2="4160" y1="2464" y2="2464" x1="3632" />
            <wire x2="4704" y1="2464" y2="2464" x1="4160" />
            <wire x2="5120" y1="2464" y2="2464" x1="4704" />
        </branch>
        <bustap x2="912" y1="2464" y2="2368" x1="912" />
        <bustap x2="1456" y1="2464" y2="2368" x1="1456" />
        <bustap x2="2016" y1="2464" y2="2368" x1="2016" />
        <bustap x2="2560" y1="2464" y2="2368" x1="2560" />
        <bustap x2="3088" y1="2464" y2="2368" x1="3088" />
        <bustap x2="3632" y1="2464" y2="2368" x1="3632" />
        <bustap x2="4160" y1="2464" y2="2368" x1="4160" />
        <bustap x2="4704" y1="2464" y2="2368" x1="4704" />
        <iomarker fontsize="28" x="5120" y="2464" name="Q(7:0)" orien="R0" />
        <iomarker fontsize="28" x="384" y="1680" name="LOAD" orien="R180" />
        <iomarker fontsize="28" x="400" y="1584" name="CLEAR" orien="R180" />
    </sheet>
</drawing>