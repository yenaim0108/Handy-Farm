import 'package:flutter/material.dart';
import 'device_scale.dart' as ds;

Color hfGreen = Color(0xFF22AA4D);
Color hfGray = Color(0xFFB7B7B7);
Color hfYellow = Color(0xFFF5AA00);
Color hfRed = Color(0xFFE8474B);
Color hfLiteGreen = Color(0xFFAACC03);
Color bgColor = Color(0xFFF5F5F5);

Widget pageButton(String text, bool isSelectedPage, bool isShort) {
  Color backgroundColor;
  double wLength;

  // 버튼 배경색 지정
  if (isSelectedPage)
    backgroundColor = hfGreen;
  else
    backgroundColor = hfGray;

  // 버튼 길이 지정
  if (isShort)
    wLength = 150.0 * ds.deviceScale;
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
              onPressed: () {},

              child: Text(
                text,
                style: TextStyle(fontSize: 15.0 * ds.deviceScale, fontWeight: FontWeight.w600),
              ),
            )
        )
      ],
    ),
  );
}

Widget interactionButton(String text, bool isConfirm, bool isShort) {
  Color backgroundColor;
  Color borderColor;
  Color textColor;
  double wLength;

  // 버튼 스타일 지정
  if (isConfirm) {
    backgroundColor = hfGreen;
    borderColor = hfGreen;
    textColor = Color(0xFFFFFFFF);
  }
  else {
    backgroundColor = bgColor;
    borderColor = hfGreen;
    textColor = hfGreen;
  }

  // 버튼 길이 지정
  if (isShort)
    wLength = 150.0 * ds.deviceScale;
  else
    wLength = 330.0 * ds.deviceScale;

  return Container (
    child: Row(
      children: <Widget>[
        ButtonTheme(
            height: 43.0 * ds.deviceScale,
            minWidth: wLength * ds.deviceScale,
            child: RaisedButton (
              shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(30.0 * ds.deviceScale),
                  side: BorderSide(color: borderColor, width: 2.0 * ds.deviceScale)
              ),
              textColor: textColor,
              color: backgroundColor,
              padding: EdgeInsets.all(8.0 * ds.deviceScale),
              onPressed: () {},

              child: Text(
                text,
                style: TextStyle(fontSize: 15.0 * ds.deviceScale, fontWeight: FontWeight.w600),
              ),
            )
        )
      ],
    ),
  );
}