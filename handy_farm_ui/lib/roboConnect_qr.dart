import 'package:flutter/material.dart';
import 'dart:async';
import 'common_ui.dart' as cui;
import 'device_scale.dart' as ds;
import 'package:qrscan/qrscan.dart' as scanner; //qrscan 패키지를 scanner 별칭으로 사용.


class roboConnect_qr  extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<roboConnect_qr> {

  String _output = 'Empty Scan Code';

  Widget certButton(String text, bool isSelectedPage, bool isShort) {
    Color backgroundColor;
    double wLength;

    // 버튼 배경색 지정
    if (isSelectedPage)
      backgroundColor = cui.hfGreen;
    else
      backgroundColor = cui.hfGray;

    // 버튼 길이 지정
    if (isShort)
      wLength = 145.0 * ds.deviceScale;
    else
      wLength = 170.0 * ds.deviceScale;

    return Container (
      child: Row(
        children: <Widget>[
          ButtonTheme(
              height: 43.0 * ds.deviceScale,
              minWidth: wLength * ds.deviceScale,
              child: RaisedButton (
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(30.0 * ds.deviceScale),
                    side: BorderSide(color: backgroundColor, width: 2.0 * ds.deviceScale)
                ),
                textColor: Color(0xFFFFFFFF),
                color: backgroundColor,
                padding: EdgeInsets.all(8.0 * ds.deviceScale),
                onPressed: () =>_scan(),

                child: Text(
                  text,
                  style: TextStyle(fontSize: 17.0 * ds.deviceScale, fontWeight: FontWeight.w600),
                ),
              )
          )
        ],
      ),
    );

  }

  Future _scan() async {
    //스캔 시작 - 이때 스캔 될때까지 blocking
    String barcode = await scanner.scan();
    //스캔 완료하면 _output 에 문자열 저장하면서 상태 변경 요청.
    Text(barcode);
  }


  @override
  initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      backgroundColor: cui.bgColor,
      body: Column(
        children: <Widget>[
          SafeArea(
              top: true,
              left: true,
              right: true,
              child: Container(
                  margin: EdgeInsets.symmetric(horizontal: 16.0),
                  height: 130.0
              )
          ),

          RichText(
            text: TextSpan(
                children: [
                  TextSpan(text: '로보연동',
                      style: TextStyle(
                        fontSize: 28.0,
                        color: cui.titleText,
                        fontWeight: FontWeight.w600,
                      ))
                ]
            ),
          ),

          SafeArea(
              top: true,
              left: true,
              right: true,
              child: Container(
                margin: EdgeInsets.symmetric(horizontal: 16.0),
                height: 30.0,
                /*child: Placeholder(),
                      Row(
                        children: <Widget>[
                          Text('hello')
                        ],
                      ),*/
              )
          ),

          SafeArea(
              top: true,
              left: true,
              right: true,
              child: Container(
                margin: EdgeInsets.symmetric(horizontal: 55.0),
                height: 30.0,
                child: Row(
                  children: <Widget>[
                    Text('* 인증방법을 선택해주세요', style: TextStyle(
                        fontSize: 20,
                        color: Color(0xFF3D3D3D),
                        fontWeight: FontWeight.w800
                    ),
                    ),

                    Spacer(), //오른쪽으로 보내기

                    Container(
                      height: 32,
                      width: 32,
                      //child: Placeholder()
                    ),
                  ],
                ),
              )

          ),

          Container(
            margin: EdgeInsets.symmetric(horizontal: 55.0),
            height: 50.0,
            child: Row(
              children: <Widget>[
                certButton("시리얼 번호", false, true),
                Spacer(),
                certButton("QR 코드 인증", true, true),
              ],
            ),
          ),

          Container(
            margin: EdgeInsets.symmetric(vertical: 20.0),
            width: 300,
            height: 300,
            decoration: BoxDecoration(
              color: Color(0xFFFFFFFF),
              borderRadius: BorderRadius.circular(30),
            ),
            child: Column(
              children: <Widget>[

              ],
            ),
          ),



        ],
      ),
    );
  }



}
