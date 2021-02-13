# jbh-util
all Methods are static type

## StringUtil

> * LPAD
>   - String lpad(int num, int size)
> - String lpad(String val, int size)
> - String lpad(int num, int size, String fillChar)
> - String lpad(String val, int size, String fillChar)

> ##### RPAD
> - String rpad(int num, int size)
> - String rpad(String val, int size)
> - String rpad(int num, int size, String fillChar)
> - String rpad(String val, int size, String fillChar)

## TypeUtil
- String toString(int num)
- String toString(long num)
- String toString(BigDecimal num)

## FileUtil
- Date getCreateTime(File file)
- Date getLastModifyTime(File file)
- String getFullName(File file)
- String getNameWithoutExtention(File file)
- String getFulPath(File file)
- String getFolderPath(File file)
- String getExtension(File file)
- File copyFile(String fromFilePath, String toFilePath) throws FileNotFoundException
- File copyFile(File fromFile, File toFile) throws FileNotFoundException

## DateTimeUtil 
- String changeTimeFormatToString(String timeStr, String bfFormat, String afFormat)
- Date transStringToDate(String date, String format) throws ParseException
- String transDateToString(Date date, String format)
- String getNowTime(String format)
- String getNowTime24HHMMSS()
- String getNowTime12HHMMSSAmPm()
- Date getNowTime()

