package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchDevice;
import com.qualcomm.robotcore.hardware.configuration.I2cSensor;
import com.qualcomm.robotcore.util.TypeConversion;

@I2cSensor(name = "Custom Color Sensor", description = "A driver for the MRI Color Sensor", xmlTag = "CustMRIcolor")
public class CustomColor extends I2cDeviceSynchDevice<I2cDeviceSynch>
{
    //private static final I2cAddr ADDRESS_I2C_DEFAULT = new I2cAddr(0x1E);
    public static final I2cAddr ADDRESS_I2C_DEFAULT = I2cAddr.create7bit(0x01E);

    private int r = 0;
    private int g = 0;
    private int b = 0;
    private int w = 0;

    public CustomColor(I2cDeviceSynch deviceClient)
    {
        super(deviceClient, true);

        this.setOptimalReadWindow();
        this.deviceClient.setI2cAddress(ADDRESS_I2C_DEFAULT);

        super.registerArmingStateCallback(false); // Deals with USB cables getting unplugged
        // Sensor starts off disengaged so we can change things like I2C address. Need to engage
        this.deviceClient.engage();
    }

    public void activeMode()
    {
        writeShort(0x03, (short)0x00); //LED on
    }

    public void passiveMode()
    {
        writeShort(0x03, (short)0x01); //LED off
    }

    public void read()
    {
        //activeMode();
        /*r = read2(0x00E);
        g = read2(0x010);
        b = read2(0x012);
        w = read2(0x014);*/
        r = readShort(0x05);
        g = readShort(0x06);
        b = readShort(0x07);
        w = readShort(0x08);
        //passiveMode();
    }

    public int red()   { return r; }
    public int green() { return g; }
    public int blue()  { return b; }
    public int white() { return w; }

    private int read2(int reg)
    {
        int o = 0;
        o += ((int)readShort(reg + 1));
        o = o << 7;
        o += ((int)readShort(reg));
        return o;
    }

    private void writeShort(int reg, short value)
    {
        //deviceClient.write(reg, TypeConversion.shortToByteArray(value));
        deviceClient.write8(reg, value);
    }

    private short readShort(int reg)
    {
        return (short)(deviceClient.read8(reg) & 0b01111111);//TypeConversion.byteArrayToShort(deviceClient.read(reg, 2));
    }

    private void setOptimalReadWindow()
    {
        // Sensor registers are read repeatedly and stored in a register. This method specifies the
        // registers and repeat read mode
        I2cDeviceSynch.ReadWindow readWindow = new I2cDeviceSynch.ReadWindow(
                0x00,
                0x1A,
                I2cDeviceSynch.ReadMode.REPEAT);
        this.deviceClient.setReadWindow(readWindow);
    }

    @Override
    public Manufacturer getManufacturer()
    {

        return Manufacturer.Adafruit; //TODO change to something different?
    }

    @Override
    protected synchronized boolean doInitialize()
    {
        return true;
    }

    @Override
    public String getDeviceName()
    {

        return "Custom color sensor driver";
    }
}