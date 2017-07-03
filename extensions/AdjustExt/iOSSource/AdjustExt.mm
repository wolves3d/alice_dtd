#import "AdjustExt.h"
#import <AdjustSdk/Adjust.h>
#include <asl.h>
#include <stdio.h>


@implementation AdjustExt


- (double) staticInit
{
	NSString *yourAppToken = @"dfzdd7h4rlkw";
	NSString *environment = ADJEnvironmentProduction;
	ADJConfig *adjustConfig = [ADJConfig configWithAppToken:yourAppToken
                                   environment:environment];

	[Adjust appDidLaunch:adjustConfig];
	
	return 1.0;
}


- (double) staticFinal
{
	// EMPTY
	return 1.0;
}


- (double) adjustEvent:(char *)arg0
{
	NSLog(@"ADJUST CUSTOM EVENT: %s", arg0);
	
	NSString *eventToken = [NSString stringWithFormat:@"%s",arg0];
	ADJEvent *event = [ADJEvent eventWithEventToken:eventToken];
	[Adjust trackEvent:event];
	
	return 1.0;
}


- (double) adjustIAP:(char*)arg0
		Arg2:(double) purchasePrice
		Arg3:(char*) purchaseCurrency
{
	NSLog(@"ADJUST IAP EVENT: %s", arg0);
	
	NSString *eventToken = [NSString stringWithFormat:@"%s", arg0];
	NSString *currency = [NSString stringWithFormat:@"%s", purchaseCurrency];
	ADJEvent *event = [ADJEvent eventWithEventToken:eventToken];
	[event setRevenue:purchasePrice currency:currency];
	
	[Adjust trackEvent:event];
	return 1.0;
}

@end