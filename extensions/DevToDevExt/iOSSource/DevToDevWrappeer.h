@interface DevToDevWrappeer : NSObject
{
}

- (double) AddTwoNumbers:(double)arg0 Arg2:(double)arg1;
- (NSString *) BuildAString:(char *)arg0 Arg2:(char *)arg1;
- (NSString *) HowManyObjects:(double)arg0 Arg2:(double)arg1 Arg3:(char *)arg2;
- (void)ReturnAsync:(double)arg0 Arg2:(double)arg1;

- (double) staticInit:(char *)arg0 Arg2:(char *)arg1;
- (double) customEvent:(char *)arg0;

@end