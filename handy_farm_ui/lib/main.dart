import 'package:flutter/material.dart';
import 'package:handyfarm/login.dart';
import 'package:handyfarm/roboConnect.dart';
import 'package:handyfarm/roboConnect_qr.dart';
import 'common_ui.dart' as cui;
import 'device_scale.dart' as ds;

void main() => runApp(roboConnect_qr());

class MyApp extends StatelessWidget {
  static const String _title = 'Flutter Code Sample';

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        backgroundColor: Color(0xFFF5F5F3),
        body: MyStatelessWidget(),
      ),
    );
  }
}

class MyStatelessWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column (
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          Row(
              mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              cui.pageButton("1:1 문의하기", false, false),
            SizedBox(width: 20.0 * ds.deviceScale),
            cui.pageButton("나의 문의내역", true, false),
            ]
            ),
          SizedBox(height: 10.0),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              cui.pageButton("생장", true, true),
              SizedBox(width: 20.0 * ds.deviceScale),
              cui.pageButton("통계", false, true)
            ],
          ),
          SizedBox(height: 10.0),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              cui.interactionButton("회원가입", false, true),
              SizedBox(width: 20.0 * ds.deviceScale),
              cui.interactionButton("로그인", true, true)
            ],
          ),
          SizedBox(height: 10.0),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              cui.interactionButton("다음", true, false)
            ],
          )
        ]
    );
  }
}
