#import "DevToDevWrappeer.h"
#import "include/core/DevToDev.h"
#include <asl.h>
#include <stdio.h>


@implementation DevToDevWrappeer

const int EVENT_OTHER_SOCIAL = 70;
extern int CreateDsMap( int _num, ... );
extern void CreateAsynEventWithDSMap(int dsmapindex, int event_index);

- (void)ReturnAsync:(double)arg0 Arg2:(double)arg1
{
    int dsMapIndex = CreateDsMap(3,
        "type", 0.0, "finished",
        "argument0", arg0, (void*)NULL,
        "argument1", arg1, (void*)NULL
	);
	
	CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
}

- (double) AddTwoNumbers:(double)arg0 Arg2:(double)arg1
{
    double value = arg0 + arg1;
    NSLog(@"yoyo: %f + %f = %f", arg0, arg1, value);

    return value;
}

- (double) customEvent:(char *)arg0
{
	NSLog(@"INIT DevToDev");
	[DevToDev initWithKey:@"applicationId" andSecretKey:@"secretKey"];
	
	NSLog(@"CUSTOM EVENT: %s", arg0);
	NSString *eventName = [NSString stringWithFormat:@"%s",arg0];
	[DevToDev customEvent:eventName];
}

- (NSString *) BuildAString:(char *)arg0 Arg2:(char *)arg1
{
    NSString *value = [NSString stringWithFormat:@"%s%s",arg0,arg1];//[arg0 stringByAppendingString:arg1];
    NSLog(@"yoyo: %s + " " + %s = %@", arg0, arg1, value);

    return value;
}

- (NSString *) HowManyObjects:(double)arg0 Arg2:(double)arg1 Arg3:(char *)arg2
{
    double value = arg0 + arg1;
    NSLog(@"yoyo: %f + %f = %f", arg0, arg1, value); 
    NSString *arg2ns = [NSString stringWithFormat:@"%s",arg2];
    NSString *myString = [NSString stringWithFormat:@"%f %@", value,arg2ns];
    NSLog(@"yoyo: %@", myString);

    return myString;

}

@end